package com.nhom11.webseller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController{
	

    @GetMapping(value = {"/", "/home"})
    public String getHome(){
        return "admin/production-management";
    }

    @GetMapping("/hello")
    public String hello(){
        return "admin/layout-page-admin";
    }

}