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
	
	public Category finById(int id) {
		return categoryRepo.findById(id).get();
	}
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
}
