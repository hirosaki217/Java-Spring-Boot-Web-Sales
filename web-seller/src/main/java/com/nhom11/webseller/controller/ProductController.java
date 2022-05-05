package com.nhom11.webseller.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhom11.webseller.dto.ProductOptionRequest;
import com.nhom11.webseller.dto.ProductRequest;
import com.nhom11.webseller.model.Manufacturer;
import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.service.ManufacturerService;
import com.nhom11.webseller.service.ProductService;
import com.nhom11.webseller.service.StorageService;

@Controller
@RequestMapping("admin/products")
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
	
	
    @GetMapping("/addForm")
    public String showFormAddProduct(Model model){
    	ProductRequest product = new ProductRequest();
    	
    	List<ProductOptionRequest> list = new ArrayList<>();
    	list.add(new ProductOptionRequest());
    	product.setOptionRequests(list);
    	model.addAttribute("product", product);
    	
        return "admin/product/add-product";
    }
    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> transferFile(String fileName){
    	Resource file = storageService.loadAsResoure(fileName);
    	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
    			"attachment; filename=\"" + file.getFilename() +"\"").body(file);
    }
    
    
    @GetMapping
    public String list(ModelMap model) {
    	List<Product> list =productService.findAll();
    	model.addAttribute("products", list);
    	return "admin/product/list-product";
    }
    
    @PostMapping("/add")
    public ModelAndView addProduct(@Valid @ModelAttribute(name = "product") ProductRequest productRequest
    		,BindingResult bindingResult){
    	
    	if(bindingResult.hasErrors()) {
    		System.out.println(bindingResult.hasErrors());
    		return new ModelAndView("admin/product/add-product") ;
    	}
    	Product product = new Product();
    	BeanUtils.copyProperties(productRequest, product);
//    	Manufacturer manufacturer = new Manufacturer();
//    	manufacturer.setId(productRequest.getManufacturerId());
//    	product.setManufacturer(manufacturer);
    	System.out.println("SIZE = " + productRequest.getOptionRequests().size());
//    	if(!productRequest.getImageFile().isEmpty()) {
//    		UUID uuid = UUID.randomUUID();
//    		String uuString = uuid.toString();
    		
//    		product.setImage(storageService.getStoredFilename(productRequest.getImageFile(), uuString));
//    		storageService.store(productRequest.getImageFile(), product.getImage());
    		
//    	}
//    	productService.save(product);

       return new ModelAndView("forward:/admin/product/add-product");
    }
}
