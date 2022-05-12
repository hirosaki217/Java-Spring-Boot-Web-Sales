package com.nhom11.webseller.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.nhom11.webseller.model.Authority;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{

//    @NotEmpty(message = "Tên Đăng Nhập Không Được Để Trống")
//	@Size(min = 4, message = "Tên Đăng Nhập Dài Hơn Hoặc Bằng 6 Ký Tự")
	private String username;

//    @NotEmpty(message = "Mật Khẩu Không Được Để Trống")
//	@Size(min = 4, message = "Mật Khẩu Dài Hơn Hoặc Bằng 8 Ký Tự")
	private String password;

	private boolean enabled =true;

	private String firstName;

	private String lastName;

	private String address;

	private String email;

	private String mobile;
	
	private String passwordConfirm;
	
	private Set<Authority> authorities; 
	
	private Date registeredAt;

	@NotEmpty
    private MultipartFile imageFile;
	private String image = "default-user.png";

	private int role;
}
