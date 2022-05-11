package com.nhom11.webseller.service;

import com.nhom11.webseller.dto.UserDto;
import com.nhom11.webseller.model.User;

public interface UserService {
	void save(UserDto userDto);

    User findByUsername(String username);
}
