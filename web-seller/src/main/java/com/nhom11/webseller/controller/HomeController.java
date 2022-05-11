package com.nhom11.webseller.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.ModelMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;
import com.nhom11.webseller.dto.CatergoryDto;
import com.nhom11.webseller.dto.ProductRequest;
import com.nhom11.webseller.model.Catergory;
import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.service.CatergoryService;
import com.nhom11.webseller.service.ManufacturerService;
import com.nhom11.webseller.service.ProductOptionService;
import com.nhom11.webseller.service.ProductService;
import com.nhom11.webseller.service.StorageService;
import org.springframework.http.HttpHeaders;

@Controller
@RequestMapping("home")
public class HomeController{
	@Autowired
	private CatergoryService catergoryService;
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private ProductService productService;

	@Autowired
	private StorageService storageService;

	

	@Autowired
	private ProductOptionService pOptionService;
	
	@ModelAttribute(name = "catergories")
	public List<CatergoryDto> getCatergories(){
		List<Catergory> catergories = catergoryService.findAll();
		List<CatergoryDto> catergoryDtos = new ArrayList<>();
		for(Catergory catergory: catergories) {
			CatergoryDto catergoryDto = new CatergoryDto();
			BeanUtils.copyProperties(catergory, catergoryDto);
		};
		System.out.println("list"+catergoryDtos.toString());
		return catergoryDtos;
	}

	// @GetMapping("/home")
	// public String showHome(){
	// 	return "index";
	// }
	@GetMapping
    public  String getAllProductsHome(ModelMap model) {
		List<Product> findAll = productService.findAll();
		List<Product> list = findAll;
		model.addAttribute("products",list);
		// ProductRequest dto = new ProductRequest();
		// model.addAttribute("products",dto);
    	return "index";
		
    }
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> transferFile(@PathVariable(name = "filename") String fileName) {
		Resource file = storageService.loadAsResoure(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}