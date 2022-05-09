package com.nhom11.webseller.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.nhom11.webseller.model.User;
import com.nhom11.webseller.repository.UserRepository;
import com.nhom11.webseller.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
