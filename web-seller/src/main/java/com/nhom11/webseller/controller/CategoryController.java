package com.nhom11.webseller.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhom11.webseller.dto.CatergoryDto;
import com.nhom11.webseller.model.Catergory;
import com.nhom11.webseller.service.CatergoryService;

@Controller
@RequestMapping("admin/catergorys")
public class CategoryController {
	@Autowired
	private CatergoryService catergoryService;
	
	
	
	@GetMapping("/add")
	public String add(Model model) {
		
		model.addAttribute("catergory", new CatergoryDto());
		return "admin/catergory/add";
	}

	@PostMapping("/saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,CatergoryDto catergoryRequest) {
		System.out.println(catergoryRequest.getId());
		Catergory catergory = new Catergory();
		catergory.setId(catergoryRequest.getId());
		BeanUtils.copyProperties(catergoryRequest, catergory);
		model.addAttribute("message","Catergory is saved!");
		catergoryService.save(catergory);
	
		return new ModelAndView("forward:/admin/catergorys",model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Catergory> list = catergoryService.findAll();
		model.addAttribute("catergorys", list);
		model.addAttribute("catergory", new CatergoryDto());
		return "admin/catergory/list";
	}
	@GetMapping("delete/{id}")
	public ModelAndView delete(ModelMap model,@PathVariable("id") Long id) {
		catergoryService.deleteById(id);
		model.addAttribute("message", "Catergory is delete!");
		return new ModelAndView("redirect:/admin/catergorys",model);
	}
	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model,@PathVariable("id") Long id) {
		Optional<Catergory> opt = catergoryService.findById(id);
		CatergoryDto dto= new CatergoryDto();
		if(opt.isPresent()) {
			Catergory enity = opt.get();
			BeanUtils.copyProperties(enity, dto);
			dto.setEdit(true);
			model.addAttribute("catergory", dto);
			return  new ModelAndView("admin/catergory/add",model);
		}
		model.addAttribute("message", "Catergory is not existed");
		return new ModelAndView("redirect:/admin/catergorys",model);
	}
	@PostMapping("/update")
    public ModelAndView updateOrUpdate(ModelMap model, CatergoryDto dto){
		Catergory catergory = new Catergory();
		catergory.setId(dto.getId());
		BeanUtils.copyProperties(dto, catergory);
		
		catergoryService.save(catergory);
    	
        return new ModelAndView("redirect:/admin/catergorys",model) ;
    }
}
