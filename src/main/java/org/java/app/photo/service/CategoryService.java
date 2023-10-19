package org.java.app.photo.service;

import java.util.List;

import org.java.app.photo.pojo.Category;
import org.java.app.photo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}
	
	public Category findById(int id) {
		return categoryRepo.findById(id).get();
	}
	
	public List<Category> findByNameContaining(String name) {
		return categoryRepo.findByNameContaining(name);
	}
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
}
