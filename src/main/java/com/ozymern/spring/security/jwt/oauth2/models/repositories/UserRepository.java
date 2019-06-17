package com.ozymern.spring.security.jwt.oauth2.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozymern.spring.security.jwt.oauth2.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// personalizado para buscar usuario por su email
	public User findByEmail(String email);
	
	
	// personalizado para buscar usuario por su username
	public User findByUsername(String username);

}
