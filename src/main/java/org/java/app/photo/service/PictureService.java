package org.java.app.photo.service;

import java.util.List;

import org.java.app.photo.pojo.Picture;
import org.java.app.photo.repo.PictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

	@Autowired
	private PictureRepo pictureRepo;
	
	public Picture save(Picture picture) {
		return pictureRepo.save(picture);
	}
	
	public List<Picture> findAll() {
		return pictureRepo.findAll();
	}
	
	public Picture findById(int id) {
		return pictureRepo.findById(id).get();
	}
	
	public List<Picture> findByTitle(String title){
		return pictureRepo.findByTitle(title);
	}
	
	public void deletePicture(Picture picture) {
		pictureRepo.delete(picture);
	}
}
