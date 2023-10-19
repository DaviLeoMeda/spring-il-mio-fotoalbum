package org.java.app.photo.mvc.auth.pojo;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Role {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@NotNull
	private String place;
	
	
	public Role() {  }
	public Role(String place) {
		setPlace(place);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "Role id [" + getId() + "]" + getPlace();
	}
	
	@Override
	public int hashCode() {
		
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Role)) return false;
		
		Role objRole = (Role) obj;
		
		return getId() == objRole.getId();
	}
	
}
