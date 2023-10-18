package org.java.app.photo.repo;

import java.util.List;

import org.java.app.photo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	public List<Category> findByName(String Name);
 	public List<Category> findByNameContaining(String Name);
}
