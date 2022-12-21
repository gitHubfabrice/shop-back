package com.fatechnologies.security.adapter.repository.impl;

import com.fatechnologies.domaine.entity.BalanceEntity;
import com.fatechnologies.security.adapter.mapper.AuthorityMapper;
import com.fatechnologies.security.adapter.mapper.UserMapper;
import com.fatechnologies.security.adapter.repository.jpa.AuthorityJpa;
import com.fatechnologies.security.adapter.repository.jpa.RoleJpa;
import com.fatechnologies.security.adapter.repository.jpa.UserJpa;
import com.fatechnologies.security.config.SecurityUtils;
import com.fatechnologies.security.domain.dto.AuthorityDto;
import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.domain.entity.Authority;
import com.fatechnologies.security.domain.entity.Role;
import com.fatechnologies.security.domain.entity.User;
import com.fatechnologies.security.exception.Exception;
import com.fatechnologies.security.exception.*;
import com.fatechnologies.security.port.UserPort;
import com.fatechnologies.security.utils.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
*<p>adaptateur des classes {@link UserDto et AccountEntity}
*
* @author Assagou Fabrice 2022-03-06
*/
@Repository
@Transactional
public class UserAdapter implements UserPort {

    private final Logger log = LoggerFactory.getLogger(UserAdapter.class);

	private final UserMapper userMapper;
	private final UserJpa userJpa;
    private final RoleJpa roleJpa;
    private final AuthorityJpa authorityJpa;
    private final AuthorityMapper authorityMapper;
    private final PasswordEncoder passwordEncoder;

	public UserAdapter(UserJpa userJpa,
                       PasswordEncoder passwordEncoder,
                       RoleJpa roleJpa,
                       AuthorityJpa authorityJpa) {
		this.userJpa = userJpa;
        this.authorityJpa = authorityJpa;
        this.roleJpa = roleJpa;
        this.passwordEncoder = passwordEncoder;
		userMapper = UserMapper.INSTANCE;
        authorityMapper = AuthorityMapper.INSTANCE;

	}

	@Override
	public void save(UserDto user) {

        var entity = userJpa.findById(user.getId()).orElseThrow();
        if(!user.getUsername().equalsIgnoreCase(entity.getUsername())){
            userJpa
                    .findOneByUsernameIgnoreCase(user.getUsername().toLowerCase())
                    .ifPresent(existingUser -> {
                        boolean removed = removeNonActivatedUser(existingUser);
                        if (!removed) {
                            throw new UsernameAlreadyUsedException();
                        }
                    });
        }

        if (!user.getEmail().equalsIgnoreCase( entity.getEmail())){
            userJpa
                    .findOneByEmailIgnoreCase(user.getEmail())
                    .ifPresent(existingUser -> {
                        boolean removed = removeNonActivatedUser(existingUser);
                        if (!removed) {
                            throw new EmailAlreadyUsedException();
                        }
                    });
        }



        var authorities = new HashSet<Authority>();
        user.getAuthoritiesString().forEach(item->{
            var dtoT = authorityJpa.findByLabelIgnoreCase(item);
            authorities.add(dtoT.get());
        });
        entity.getAuthorities().clear();
        var role = roleJpa.findOneByLabelIgnoreCase(user.getRoleLabel()).orElseThrow();
        entity.setRole(role);
        var authoritiesRole = authorityJpa.findAllByRoleId(role.getId());
        entity.getAuthorities().addAll(authoritiesRole);

        entity.setEmail(user.getEmail());
        entity.setProfil(user.getProfil());
        entity.setUsername(user.getUsername());
        entity.setGender(user.getGender());
        var balance = new BalanceEntity();
        entity.setBalance(balance);
        entity.getAuthorities().addAll(authorities);

		userJpa.save(entity);
	}

	@Override
	public UserDto getById(UUID id) {

        var user = userJpa.findById(id).orElseThrow(() -> new Exception("Veuillez contacter l'administrateur"));
        var dto = userMapper.modelToDto(user);
        if(!user.getAuthorities().isEmpty()){
            var authorities = new HashSet<AuthorityDto>();
            for (Authority authority : user.getAuthorities()) {
                var authorityOptional = authorityJpa.findById(authority.getId());
                if (authorityOptional.isPresent()){
                    var auth = authorityMapper.modelToDto(authorityOptional.get());
                    authorities.add(auth);
                    dto.getAuthorities().clear();
                    dto.getAuthorities().addAll(authorities);
                }
            }
        }
        return dto;
	}

	@Override
	public void delete(UUID apartmentId) {
		userJpa.deleteById(apartmentId);
	}

    @Override
	public List<UserDto> getAll() {
        var accountEntities = userJpa.findAll();
        var users = new ArrayList<UserDto>();
        for (User accountEntity : accountEntities) {
            var dto = userMapper.modelToDto(accountEntity);
            var authorities = new HashSet<AuthorityDto>();
            if(!accountEntity.getAuthorities().isEmpty()){
                for (Authority authority : accountEntity.getAuthorities()) {
                    var authorityOptional = authorityJpa.findById(authority.getId());
                    if (authorityOptional.isPresent()){
                        var auth = authorityMapper.modelToDto(authorityOptional.get());
                        authorities.add(auth);
                        dto.getAuthorities().clear();
                        dto.getAuthorities().addAll(authorities);
                    }
                }
            }
            users.add(dto);
        }
        return users;
	}

    @Override
    public User registerAccount(UserDto userDTO, String password) {
        userJpa
                .findOneByUsernameIgnoreCase(userDTO.getUsername().toLowerCase())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw new UsernameAlreadyUsedException();
                    }
                });
        userJpa
                .findOneByEmailIgnoreCase(userDTO.getEmail())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw new EmailAlreadyUsedException();
                    }
                });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(userDTO.getUsername().toLowerCase());
        // new user gets initially a generated password
        newUser.setId(userDTO.getId());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setProfil(userDTO.getProfil());
        // new user is not active
        newUser.setActivated(true);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generatePassword());
        Role role;
        role = roleJpa.findOneByLabelIgnoreCase(userDTO.getRoleLabel()).orElseThrow();

        var balance = new BalanceEntity();
        newUser.setBalance(balance);
        Set<Authority> authorities;
        authorities = authorityJpa.findAllByRoleId(role.getId());
        newUser.setAuthorities(authorities);


        userJpa.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    @Override
    public User createUser(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setProfil(userDTO.getProfil());

        String encryptedPassword = passwordEncoder.encode("1234");
        user.setPassword(encryptedPassword);
        user.setActivated(true);
        Role role;
        role = roleJpa.findOneByLabelIgnoreCase(userDTO.getRoleLabel()).orElseThrow();
        if (!userDTO.getAuthoritiesString().isEmpty()) {
            Set<Authority> authorities = userDTO
                    .getAuthoritiesString()
                    .stream()
                    .map(authorityJpa::findByLabelIgnoreCase)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setRole(role);
            user.setAuthorities(authorities);
            var balance = new BalanceEntity();
            user.setBalance(balance);
            userJpa.save(user);
            log.debug("Created Information for User: {}", user);
            return user;
        }else{
            throw new AuthorityException();
        }
    }

    @Override
    public void updatePassword(UserDto userDTO) {
        var entity = userJpa.findById(userDTO.getId());
        if ( passwordEncoder.matches(userDTO.getPasswordOld(), entity.get().getPassword())){
                String encryptedPassword = passwordEncoder.encode(userDTO.getNewPassword());
                entity.get().setPassword(encryptedPassword);
                userJpa.save(entity.get());
                return;
            }
        throw new Exception("votre mot de passe actuel n'est pas correcte");
    }

    @Override
    public User updateUser(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setProfil(userDTO.getProfil());

        user.setPassword(userDTO.getPassword());
        user.setActivated(true);
        if (!userDTO.getAuthoritiesString().isEmpty()) {
            Set<Authority> authorities = userDTO
                    .getAuthoritiesString()
                    .stream()
                    .map(authorityJpa::findByLabelIgnoreCase)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setAuthorities(authorities);
            userJpa.save(user);
            log.debug("Created Information for User: {}", user);
            return user;
        }else{
            throw new AuthorityException();
        }
    }


    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userJpa.delete(existingUser);
        userJpa.flush();
        return true;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<UserDto> getAllManagedUsers(Pageable pageable) {
        return userJpa.findAll(pageable).map(UserDto::new);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<UserDto> getAllPublicUsers(Pageable pageable) {
        return userJpa.findAllByIdNotNullAndActivatedIsTrue(pageable).map(UserDto::new);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String username) {
        return userJpa.findOneWithAuthoritiesByUsername(username);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userJpa::findOneWithAuthoritiesByUsername);
    }

    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<String> getAuthorities() {
        return authorityJpa.findAll().stream().map(Authority::getLabel).collect(Collectors.toList());
    }

    @org.springframework.transaction.annotation.Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userJpa::findOneByUsernameIgnoreCase)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    log.debug("Changed password for User: {}", user);
                });
    }
}
