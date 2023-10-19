package org.java.app.photo.mvc.controller;

import java.util.List;


import org.java.app.photo.pojo.Category;
import org.java.app.photo.pojo.Picture;
import org.java.app.photo.service.CategoryService;
import org.java.app.photo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pictures")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String getIndex(
			Model model,
			@RequestParam(required = false) String search) {
		List<Picture> pictures = search == null
				? pictureService.findAll()
				: pictureService.findByTitle(search);
		
		List<Category> categories = search == null
				? categoryService.findAll()
				: categoryService.findByNameContaining(search);
				
		model.addAttribute("picture", pictures);
		model.addAttribute("category", categories);
		
		return "photo-index";
	}
	
	@GetMapping("/pictures/{id}")
	public String getShow(
			@PathVariable int id, Model model) {
		
		Picture picture = pictureService.findById(id);
		model.addAttribute("picture", picture);
		
		return "photo-show";
		
	}
	
	@GetMapping("/create")
	public String getCreate(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("picture", new Picture());
		model.addAttribute("categories", categories);
		
		return "photo-create";
	}
	
	@PostMapping("/create")
	public String storePhoto(
			@Valid @ModelAttribute Picture formPhoto,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error: ");
			bindingResult.getAllErrors().stream()
			.map(e -> e.getDefaultMessage())
			.forEach(System.out::println);
		
			
			return "photo-create";
		} else {
			System.out.println("Data confirmed.");
		}	
		
		try {pictureService.save(formPhoto);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		return "redirect:/pictures";
	}
	
	@GetMapping("/update/{id}")
	public String getUpdate(
			@PathVariable int id,
			Model model) {
		
		List<Category> categories = categoryService.findAll();
		Picture picture = pictureService.findById(id);
		
		model.addAttribute("picture", picture);
		model.addAttribute("categories", categories);
		
		return "photo-create";
	}
	
	@PostMapping("/update/{id}")
	public String updatePhoto(
			@Valid @ModelAttribute Picture formPhoto,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error: ");
			bindingResult.getAllErrors().stream()
			.map(e -> e.getDefaultMessage())
			.forEach(System.out::println);
			
			return "photo-create";
		} else {
			System.out.println("Data confirmed");
		}
		
		System.out.println(formPhoto);
		
		try {pictureService.save(formPhoto);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/picture";
	}
	
	@PostMapping("/delete/{id}")
	public String deletePhoto(@PathVariable int id) {
		
		Picture picture = pictureService.findById(id);
		
		pictureService.deletePicture(picture);
		
		
		return "redirect:/pictures";
	}
	
}
