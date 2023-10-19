package org.java.app.photo.pojo;

import java.util.Arrays;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Category {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(mappedBy = "categories")
	@JsonManagedReference
	private List<Picture> pictures;
	
	private String name;
	
	public Category() {  }
	public Category(String name, Picture...pictures) {
		
		setName(name);
		setPictures(Arrays.asList(pictures));
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasPicture(Picture picture) {
		if (getPictures() == null) return false;
		
		for (Picture pic : getPictures())
			if (picture.getId() == pic.getId())
				return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + getId() +"]"
				+ "," + getName();
	}
	
}
