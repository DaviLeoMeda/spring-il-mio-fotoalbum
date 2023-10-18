package org.java.app.photo.mvc.controller;

import java.util.List;

import org.java.app.photo.pojo.Picture;
import org.java.app.photo.service.CategoryService;
import org.java.app.photo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/photos")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String getIndex(
			Model model,
			@RequestParam(required = false) String title) {
		List<Picture> pictures = title == null
				? pictureService.findAll()
				: pictureService.findByTitle(title);
				
		model.addAttribute("pictures", pictures);
		model.addAttribute("title", title);
		
		return "photo-index";
	}
	
	
}
