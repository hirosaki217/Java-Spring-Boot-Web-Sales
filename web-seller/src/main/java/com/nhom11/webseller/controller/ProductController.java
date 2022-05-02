package com.nhom11.webseller.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhom11.webseller.dto.ProductRequest;
import com.nhom11.webseller.model.Manufacturer;
import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.service.ManufacturerService;
import com.nhom11.webseller.service.ProductService;
import com.nhom11.webseller.service.StorageService;

@Controller
public class ProductController {
	
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StorageService storageService;
	
	@ModelAttribute("manufacturers")
    public List<Manufacturer> getProducts(){
    	return manufacturerService.findAll().stream().map(item->{
    		Manufacturer request = new Manufacturer();
    		BeanUtils.copyProperties(item, request);
    		return request;
    	}).collect(Collectors.toList());
    }
	
	
    @GetMapping("/add")
    public String showFormAddProduct(Model model){
    	ProductRequest product = new ProductRequest();
    	model.addAttribute("product", product);
    	
        return "admin/add-product";
    }
    
    
    @PostMapping("/admin/products")
    public ModelAndView addProduct(@Valid @ModelAttribute(name = "product") ProductRequest productRequest
    		,BindingResult bindingResult){
    	
    	if(bindingResult.hasErrors()) {
    		System.out.println(bindingResult.hasErrors());
//    		return new ModelAndView("admin/add-product") ;
    	}
//    	Product product = new Product();
//    	BeanUtils.copyProperties(productRequest, product);
//    	Manufacturer manufacturer = new Manufacturer();
//    	manufacturer.setId(productRequest.getManufacturerId());
//    	product.setManufacturer(manufacturer);
//    	
//    	
//    	if(!productRequest.getImageFile().isEmpty()) {
//    		UUID uuid = UUID.randomUUID();
//    		String uuString = uuid.toString();
//    		
//    		product.setImage(storageService.getStoredFilename(productRequest.getImageFile(), uuString));
//    		storageService.store(productRequest.getImageFile(), product.getImage());
//    		
//    	}
//    	productService.save(product);
       System.out.println(productRequest.toString());
       System.out.println("da vao trang nay");
       return new ModelAndView("admin/add-product") ;
    }
}
