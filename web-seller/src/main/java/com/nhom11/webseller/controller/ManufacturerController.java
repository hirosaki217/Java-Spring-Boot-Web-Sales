package com.nhom11.webseller.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhom11.webseller.dto.ManufacturerDto;
import com.nhom11.webseller.dto.ProductRequest;
import com.nhom11.webseller.model.Manufacturer;
import com.nhom11.webseller.model.Product;
import com.nhom11.webseller.service.ManufacturerService;
import com.nhom11.webseller.service.StorageService;

@Controller
@RequestMapping("admin/manufacturers")
public class ManufacturerController {
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private StorageService storageService;

	@GetMapping
    public String getManufacturers(ModelMap model){
		List<Manufacturer> list = manufacturerService.findAll();
		model.addAttribute("manufacturers",list);
		ManufacturerDto dto = new ManufacturerDto();
		model.addAttribute("manufacturer",dto);
    	return "admin/manufacturer/list-manufacturer";
    }
	@GetMapping("/addForm")
    public String showFormAddManufacturer(Model model){
		ManufacturerDto dto = new ManufacturerDto();
    	model.addAttribute("manufacturer", dto);
    	
        return "admin/manufacturer/add-manufacturer";
    }
	@GetMapping("/updateForm")
    public String showFormUpdateManufacturer(Model model){
		ManufacturerDto dto = new ManufacturerDto();
    	model.addAttribute("manufacturer", dto);
    	
        return "admin/manufacturer/update-manufacturer";
    }
	@GetMapping("/update/{id}")
	public ModelAndView updateManufacturer(ModelMap model, @PathVariable("id") Long id) {
		Optional<Manufacturer> op = manufacturerService.findById(id);
		ManufacturerDto dto = new ManufacturerDto();
		if(op.isPresent()) {
			Manufacturer entity = op.get();
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("manufacturer",dto);
			return new ModelAndView("admin/manufacturer/update-manufacturer");
		}
		model.addAttribute("message","Manufacturer not exist");
		
		return new ModelAndView("forward:/admin/manufacturers",model);
	}


	@PostMapping("/save")
    public ModelAndView saveOrUpdate(ModelMap model, ManufacturerDto dto){
		
    	
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(0);
		BeanUtils.copyProperties(dto, manufacturer);
		manufacturerService.save(manufacturer);
    	
        return new ModelAndView("redirect:/admin/manufacturers",model) ;
    }
	
	@PostMapping("/update")
    public ModelAndView updateOrUpdate(ModelMap model, ManufacturerDto dto){
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setId(dto.getId());
		BeanUtils.copyProperties(dto, manufacturer);
		
		manufacturerService.save(manufacturer);
    	
        return new ModelAndView("redirect:/admin/manufacturers",model) ;
    }
	@GetMapping("/search")
	public String findByName(Model model, @RequestParam(name="name",required=false) String name) {
		List<Manufacturer> list =null;
		System.out.println("SEARCH: "+name);
		if(StringUtils.hasText(name)) {
			list = manufacturerService.findByNameContaining(name);
		}
		else {
			list = manufacturerService.findAll();
			
		}
		model.addAttribute("manufacturers",list);
		return "admin/manufacturer/list-manufacturer";
	}
	@GetMapping("delete/{id}")
	public ModelAndView delete(ModelMap model ,@PathVariable("id") Long id) {
		manufacturerService.deleteById(id);
		model.addAttribute("message","Manufacturer is deleted");
		return new ModelAndView("redirect:/admin/manufacturers",model);
	}
	
}
