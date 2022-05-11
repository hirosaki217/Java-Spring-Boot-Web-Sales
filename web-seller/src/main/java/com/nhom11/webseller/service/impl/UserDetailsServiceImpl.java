package com.nhom11.webseller.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nhom11.webseller.model.Authority;
import com.nhom11.webseller.model.User;
import com.nhom11.webseller.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) throw new UsernameNotFoundException(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Authority authority : user.getAuthorities()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword().trim(), grantedAuthorities);
	}
	
}
