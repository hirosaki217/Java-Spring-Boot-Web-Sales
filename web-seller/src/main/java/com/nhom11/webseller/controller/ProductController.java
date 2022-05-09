package com.nhom11.webseller.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("products")
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

	@GetMapping("/admin/addForm")
	public String showFormAddProduct(Model model) {
		ProductRequest product = new ProductRequest();
		product.setEdit(false);
		List<ProductOptionRequest> list = new ArrayList<>();
		list.add(new ProductOptionRequest());
		product.setOptionRequests(list);
		model.addAttribute("product", product);

		return "admin/product/add-product";
	}

	@GetMapping("/admin/edit/{id}")
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

	@GetMapping("/admin/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		Product p = new Product();
		p.setId(id);

		pOptionService.deleteProductOptionByProductID(id);
		productService.delete(p);

		return "redirect:/products/admin";
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> transferFile(@PathVariable(name = "filename") String fileName) {
		Resource file = storageService.loadAsResoure(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/admin")
	public String list(ModelMap model) {
		List<Product> list = productService.findAll();
		model.addAttribute("products", list);
		return "admin/product/list-product";
	}

	@PostMapping("/admin/add")
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
				
				ProductOption option = options.get(i);
				if(optionRequest.getImageFile().isEmpty() && optionRequest.getImage() != null && productRequest.getIsEdit()) {
					option.setImage(optionRequest.getImage());
				}
				else if(
						((!optionRequest.getImageFile().isEmpty()) && optionRequest.getImage() != null) || 
						(!optionRequest.getImageFile().isEmpty()) && optionRequest.getImage() == null)	{
					UUID uuid = UUID.randomUUID();
					String uuString = uuid.toString();
					try {
						if(optionRequest.getImage() != null)
							storageService.delete(optionRequest.getImage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
					option.setImage(storageService.getStoredFilename(optionRequest.getImageFile(), uuString));
					storageService.store(optionRequest.getImageFile(), option.getImage());
					
				}
				
			}
		}

		// set product option into product
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

		return new ModelAndView("redirect:/products/admin");
	}


	@GetMapping({"/", ""})
	public String showProduct(Model model, 
			@RequestParam(name = "catergoryId", required = false) Optional<Long> catergoryId, 
			@RequestParam(name = "page", required = false) Optional<Integer> page,  
			@RequestParam(name = "size", required = false) Optional<Integer> size,
			@RequestParam(name = "sort", required = false) Optional<String> sort
			){
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		long cId = catergoryId.orElse((long) 0);
		String sortType = null;
		String sortBy = "name";
		Pageable pageable = PageRequest.of(currentPage -1, pageSize, Sort.by(sortBy));
		if(sort.isPresent()) {
			String[] temp = sort.get().split("\\.");
			sortBy = temp[0];
			sortType = temp[1];
			System.out.println("SORT TYPE: "+ sortType);
			switch (sortType) {
			case "DESC":
				pageable = PageRequest.of(currentPage -1, pageSize, Sort.by(sortBy).descending());
				break;

			default:
				pageable = PageRequest.of(currentPage -1, pageSize, Sort.by(sortBy));
				break;
			}
		}
		

		
		Page<Product> resultPage = null;
		
		if(cId!=0) {
			resultPage = productService.findByCatergoryId( cId, pageable);
		}else {
			resultPage = productService.findAll(pageable);
		}
		System.out.println(resultPage.getNumber());
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0){
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage + 2, totalPages);

			if(totalPages > 5){
				if(end == totalPages) start = end -5;
				else if(start == 1) end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
												.boxed()
												.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
			model.addAttribute("catergoryId", cId);
		}	
		model.addAttribute("productPage", resultPage);
		
		return "sanpham";
	}
	
	@GetMapping("/{id}")
	public String showProduct(@PathVariable long id, Model model) {
		Optional<Product> p = productService.findById(id);
		if(p.isPresent()) {
			model.addAttribute("product", p);
		}
		return "ct-sanpham";
	}

}
