package com.proyects.ecommerce.services.adminCategory;

import com.proyects.ecommerce.dto.CategoryDto;
import com.proyects.ecommerce.entity.Category;

public interface CategoryService {

	Category createCategory(CategoryDto categoryDto);
}
