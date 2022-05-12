package com.nhom11.webseller.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhom11.webseller.dto.UserDto;
import com.nhom11.webseller.model.User;
import com.nhom11.webseller.repository.UserRepository;
import com.nhom11.webseller.service.StorageService;
import com.nhom11.webseller.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private StorageService storageService;
    
    @Override
    public void save(UserDto userDto) {
    	userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setAuthorities(userDto.getAuthorities());
        user.setImage(userDto.getImage());
        UUID uuid = UUID.randomUUID();
		String uuString = uuid.toString();
       
        if(!userDto.getImageFile().isEmpty()) {
        	user.setImage(storageService.getStoredFilename(userDto.getImageFile(), uuString));
         	storageService.store(userDto.getImageFile(), user.getImage());
        }
        	

        	
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
    	User user = userRepository.findByUsername(username);
        return user;
    }
}
