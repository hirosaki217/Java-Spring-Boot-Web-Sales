package com.nhom11.webseller.controller;

import java.util.List;


import com.nhom11.webseller.model.User;
import com.nhom11.webseller.service.StorageService;
import com.nhom11.webseller.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin/users")
public class UserController {

	@Autowired
	private UserService userService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> transferFile(@PathVariable(name = "filename") String fileName) {
		Resource file = storageService.loadAsResoure(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping
    public String list(ModelMap model){
        List<User> list = userService.findAll();
		model.addAttribute("users", list);
        System.out.println(list);
        return "admin/user/list-user";
    }
}
