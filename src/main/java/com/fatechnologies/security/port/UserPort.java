package com.fatechnologies.security.port;

import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserPort {
	
	UserDto getById(UUID id);
    void save(UserDto dto);
    User registerAccount(UserDto userDTO, String password);
    User createUser(UserDto dto);
    void updatePassword(UserDto userDTO);
    User updateUser(UserDto dto);
    List<UserDto> getAll();
	void delete(UUID accountId);
}