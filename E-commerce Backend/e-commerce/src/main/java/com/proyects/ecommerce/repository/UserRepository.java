package com.proyects.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyects.ecommerce.entity.User;
import com.proyects.ecommerce.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findFirstByEmail(String email);
	
	User findByRole(UserRole userRole);
}
