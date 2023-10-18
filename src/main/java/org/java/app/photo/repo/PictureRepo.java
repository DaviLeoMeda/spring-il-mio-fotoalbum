package org.java.app.photo.repo;

import java.util.List;

import org.java.app.photo.pojo.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Integer> {

 	public List<Picture> findByTitle(String Title);
 	public List<Picture> findByTitleContaining(String Title);
}
