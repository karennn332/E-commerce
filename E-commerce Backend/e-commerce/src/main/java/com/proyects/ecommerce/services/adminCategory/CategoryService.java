package com.proyects.ecommerce.services.adminCategory;

import java.util.List;

import com.proyects.ecommerce.dto.CategoryDto;
import com.proyects.ecommerce.entity.Category;

public interface CategoryService {

	Category createcategory(CategoryDto categoryDto);
	
	 List<Category> getAllCategories();
}
