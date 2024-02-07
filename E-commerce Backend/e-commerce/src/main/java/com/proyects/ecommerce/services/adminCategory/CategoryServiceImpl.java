package com.proyects.ecommerce.services.adminCategory;

import org.springframework.stereotype.Service;

import com.proyects.ecommerce.dto.CategoryDto;
import com.proyects.ecommerce.entity.Category;
import com.proyects.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Category createcategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		
		return categoryRepository.save(category);
	}
	
	

	
	
}
