package com.proyects.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyects.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
