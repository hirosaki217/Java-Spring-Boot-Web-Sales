package com.nhom11.webseller.service;

public interface SecurityService {
	boolean isAuthenticated();
    void autoLogin(String username, String password);
}
