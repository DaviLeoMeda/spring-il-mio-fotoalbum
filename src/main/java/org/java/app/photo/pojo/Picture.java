package org.java.app.photo.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Picture {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(length = 50, unique = true, nullable = false)
	@Length(min=1,
			max=50,
			message = "Title Name has to be between 1 and 50 characters")
	@NotBlank
	private String title;
	
	@Length(min=0,
			max=128,
			message = "Lunghezza minima di 1 carattere, massima di 50")
	private String description;
	
	@Column(length = 128 , nullable = false)
	@Length(min=1,
			max=128,
			message = "URL has to be between 1 and 128 characters")
	@NotBlank
	private String url;
	
	private boolean visible;
	
	@ManyToMany
	private List<Category> categories;
	
	public Picture() {  }
	public Picture( String title, String description, 
					String url, boolean visible, Category...categories) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setCategories(Arrays.asList(categories));
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
//	public boolean hasCategory(Category categories) {
//		if (getCategories() == null) return false;
//		
//		for (Category cat : getCategories())
//			if (category.getId() == cat.getId())
//				return true;
//		
//		return false;
//	}
	
	@Override
	public String toString() {
		return "Photo [id=" + getId() +"]"
				+ "," + getTitle()
				+ "," + getDescription()
				+ "," + getUrl()
				+ "," + isVisible();
	}
	
}
