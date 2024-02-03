package com.proyects.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyects.ecommerce.dto.CategoryDto;
import com.proyects.ecommerce.entity.Category;
import com.proyects.ecommerce.services.adminCategory.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

	private final CategoryService categoryService;
	
	
	@PostMapping("category")
	public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
		Category category = categoryService.createCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
}
