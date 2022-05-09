package com.nhom11.webseller.dto;

import com.nhom11.webseller.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityRequest {
    private User user;
	private String authority;
}
