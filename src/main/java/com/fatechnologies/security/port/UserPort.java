package com.fatechnologies.security.port;

import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.domain.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserPort {
	
	UserDto getById(UUID id);
    void save(UserDto dto);
    UserEntity registerAccount(UserDto userDTO, String password);
    UserEntity createUser(UserDto dto);
    void updatePassword(UserDto userDTO);
    UserEntity updateUser(UserDto dto);
    List<UserDto> getAll();
	void delete(UUID accountId);
}