package org.java.app.photo.mvc.auth.repo;


import org.java.app.photo.mvc.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}

