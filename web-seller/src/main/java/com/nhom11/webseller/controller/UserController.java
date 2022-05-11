package com.nhom11.webseller.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhom11.webseller.dto.UserDto;
import com.nhom11.webseller.model.Authority;
import com.nhom11.webseller.model.User;
import com.nhom11.webseller.service.SecurityService;
import com.nhom11.webseller.service.UserService;
import com.nhom11.webseller.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new UserDto());

        return "/admin/user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userForm.setAuthorities(getAuthorities(userForm.getRole()));
        userService.save(userForm);
        
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String showFormLogin(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "admin/user/login";
    }
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "admin/user/welcome";
    }
    
    @GetMapping("/access-denied")
    @ResponseBody
    public String denied() {
    	return "bạn cần đăng nhập với tài khoản có quyền cao hơn hoặc thử lại sau";
    }
    
    public Set<Authority> getAuthorities(int role){
    	Set<Authority> authorities =  null;
		Authority ROLE_ADMIN = new Authority("ROLE_ADMIN");	
		Authority ROLE_MANAGER = new Authority("ROLE_MANAGER");
		Authority ROLE_EMPLOYEE = new Authority("ROLE_EMPLOYEE");
		Authority ROLE_CUSTOMER = new Authority("ROLE_CUSTOMER");
    	switch (role) {
		case 1:
			authorities= new HashSet<Authority>();
			authorities.add(ROLE_ADMIN);
			break;
		case 2:
			authorities= new HashSet<Authority>();
			authorities.add(ROLE_MANAGER);
			break;
		case 3:
			authorities= new HashSet<Authority>();
			authorities.add(ROLE_EMPLOYEE);
			break;
		default:
			authorities= new HashSet<Authority>();
			authorities.add(ROLE_CUSTOMER);
			break;
		}
    	return authorities;
    }
}