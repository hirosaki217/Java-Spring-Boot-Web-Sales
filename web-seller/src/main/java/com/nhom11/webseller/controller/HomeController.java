package com.nhom11.webseller.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nhom11.webseller.dto.CatergoryDto;
import com.nhom11.webseller.model.Catergory;
import com.nhom11.webseller.service.CatergoryService;
import com.nhom11.webseller.service.ProductOptionService;
import com.nhom11.webseller.service.ProductService;


@Controller
public class HomeController{
	@Autowired
	private CatergoryService catergoryService;
	
	
	@ModelAttribute(name = "catergories")
	public List<CatergoryDto> getCatergories(){
		List<Catergory> catergories = catergoryService.findAll();
		List<CatergoryDto> catergoryDtos = new ArrayList<>();
		for(Catergory catergory: catergories) {
			CatergoryDto catergoryDto = new CatergoryDto();
			BeanUtils.copyProperties(catergory, catergoryDto);
		};
		return catergoryDtos;
	}

}