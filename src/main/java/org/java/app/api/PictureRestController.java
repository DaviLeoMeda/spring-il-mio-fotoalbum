package org.java.app.api;

import java.util.List;
import java.util.Optional;

import org.java.app.api.dto.PictureDTO;
import org.java.app.photo.pojo.Picture;
import org.java.app.photo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class PictureRestController {
	
	@Autowired
	private PictureService pictureService;
	
	@GetMapping
	public ResponseEntity<List<Picture>> getAll() {
		
		List<Picture> pictures = pictureService.findAll();
		
		return new ResponseEntity<>(pictures, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> save(
			@RequestBody PictureDTO pictureDto
		) {
		
		Picture picture = new Picture(pictureDto);
		
		System.out.println("Api book SAVE:\n" + picture);
		picture = pictureService.save(picture);
		
		return new ResponseEntity<>(picture.getId(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Picture> getPicture(
			@PathVariable int id) {
		
		Optional<Picture> optPhoto = pictureService.findById(id);
		
		if (optPhoto.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(optPhoto.get(), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Picture> updatePicture(
			@PathVariable int id,
			@RequestBody PictureDTO pictureDto
		) {
		
		Optional<Picture> optPhoto = pictureService.findById(id);
		
		if (optPhoto.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Picture picture = optPhoto.get();
		picture.fillFromPictureDto(pictureDto);
	
		try {
		System.out.println("update success");
			picture = pictureService.save(picture);
			
			return new ResponseEntity<>(picture, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePicture(
			@PathVariable int id
		) {
		
		Optional<Picture> optPhoto = pictureService.findById(id);
		
		if (optPhoto.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Picture picture = optPhoto.get();
		pictureService.deletePicture(picture);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
