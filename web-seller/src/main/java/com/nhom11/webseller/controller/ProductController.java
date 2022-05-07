package com.nhom11.webseller.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhom11.webseller.dto.ProductOptionRequest;
import com.nhom11.webseller.dto.ProductRequest;
import com.nhom11.webseller.model.Catergory;
import com.nhom11.webseller.model.Manufacturer;
import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.model.ProductOption;
import com.nhom11.webseller.service.CatergoryService;
import com.nhom11.webseller.service.ManufacturerService;
import com.nhom11.webseller.service.ProductOptionService;
import com.nhom11.webseller.service.ProductService;
import com.nhom11.webseller.service.StorageService;

import groovyjarjarpicocli.CommandLine.Parameters;

@Controller
@RequestMapping("admin/products")
public class ProductController {

	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private ProductService productService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private CatergoryService catergoryService;

	@Autowired
	private ProductOptionService pOptionService;

	@ModelAttribute("manufacturers")
	public List<Manufacturer> getProducts() {
		return manufacturerService.findAll().stream().map(item -> {
			Manufacturer request = new Manufacturer();
			BeanUtils.copyProperties(item, request);
			return request;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("catergories")
	public List<Catergory> getCatergories() {
		return catergoryService.findAll().stream().map(item -> {
			Catergory catergory = new Catergory();
			BeanUtils.copyProperties(item, catergory);
			return catergory;
		}).collect(Collectors.toList());
	}

	@GetMapping("/addForm")
	public String showFormAddProduct(Model model) {
		ProductRequest product = new ProductRequest();
		product.setEdit(false);
		List<ProductOptionRequest> list = new ArrayList<>();
		list.add(new ProductOptionRequest());
		product.setOptionRequests(list);
		model.addAttribute("product", product);

		return "admin/product/add-product";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showFormEdit(ModelMap model, @PathVariable long id) {
		Optional<Product> productO = productService.findById(id);
		Product product = productO.get();
		ProductRequest productRequest = new ProductRequest();
		BeanUtils.copyProperties(product, productRequest);

		productRequest.setEdit(true);
		List<ProductOption> productOptions = product.getProductOptions();
		List<ProductOptionRequest> list = new ArrayList<>();
		for (ProductOption productOption : productOptions) {
			ProductOptionRequest optionRequest = new ProductOptionRequest();
			BeanUtils.copyProperties(productOption, optionRequest);
			optionRequest.setProductId(productOption.getProduct().getId());

			list.add(optionRequest);
		}
		
		productRequest.setOptionRequests(list);
		model.addAttribute("product", productRequest);
		return new ModelAndView("/admin/product/add-product", model);
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		Product p = new Product();
		p.setId(id);

		pOptionService.deleteProductOptionByProductID(id);
		productService.delete(p);

		return "redirect:/admin/products";
	}

	@GetMapping("/images/{filename:.+}")
	public ResponseEntity<Resource> transferFile(String fileName) {
		Resource file = storageService.loadAsResoure(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping
	public String list(ModelMap model) {
		List<Product> list = productService.findAll();
		model.addAttribute("products", list);
		return "admin/product/list-product";
	}

	@PostMapping("/add")
	public ModelAndView addProduct(ModelMap model, @ModelAttribute(name = "product") ProductRequest productRequest,
			BindingResult bindingResult) {

		long prId = productRequest.getId();
		if (productRequest.getManufacturerId() == 0)
			bindingResult.rejectValue("manufacturerId", "error.productRequest", "bạn cần chọn nhà sản xuất");
		if (productRequest.getCatergoryId() == 0)
			bindingResult.rejectValue("catergoryId", "error.productRequest", "bạn cần chọn loại sản phẩm");
		if (bindingResult.hasErrors()) {
			if (prId > 0) {
				productRequest.setEdit(true);
				model.addAttribute("product", productRequest);
			}
			return new ModelAndView("/admin/product/add-product", model);
		}

		Product product = new Product();
		List<ProductOption> options = new ArrayList<>();
		BeanUtils.copyProperties(productRequest, product);

		// init manufacturer
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(productRequest.getManufacturerId());
		product.setManufacturer(manufacturer);

		// init catergory
		Catergory catergory = new Catergory();
		catergory.setId(productRequest.getCatergoryId());
		product.setCatergory(catergory);

		// remove null
		List<ProductOptionRequest> optionRequests = productRequest.getOptionRequests();
		for (int i = 0; i < optionRequests.size(); i++) {
			ProductOptionRequest o = optionRequests.get(i);
			if (o.getColor() == null || o.getSku() == null || o.getImageFile() == null) {
				optionRequests.remove(i);
				i = 0;
			}

		}
		// copy from dto
		for (ProductOptionRequest optionRequest : optionRequests) {
			ProductOption pOption = new ProductOption();
			BeanUtils.copyProperties(optionRequest, pOption);
			options.add(pOption);
		}

		// save image from dto
		for (int i = 0; i < optionRequests.size(); i++) {
			ProductOptionRequest optionRequest = optionRequests.get(i);
			if (!optionRequest.getImageFile().isEmpty()) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				ProductOption option = options.get(i);
				option.setImage(storageService.getStoredFilename(optionRequest.getImageFile(), uuString));
				storageService.store(optionRequest.getImageFile(), option.getImage());
			}
		}

		// set product option into product
//		product.setProductOptions(options);
		Product p = productService.save(product);

		long productId = p.getId();
		pOptionService.deleteProductOptionByProductID(productId);
		for(int i=0;i< options.size(); i++) {
			Product p1 = new Product();
			p1.setId(productId);
			ProductOption option = options.get(i);
			
			option.setProduct(p1);
			pOptionService.save(option);
		}

		return new ModelAndView("redirect:/admin/products");
	}

}
