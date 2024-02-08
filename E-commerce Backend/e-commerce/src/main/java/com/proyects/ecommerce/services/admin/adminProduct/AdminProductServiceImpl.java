package com.proyects.ecommerce.services.admin.adminProduct;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyects.ecommerce.dto.ProductDto;
import com.proyects.ecommerce.entity.Category;
import com.proyects.ecommerce.entity.Product;
import com.proyects.ecommerce.repository.CategoryRepository;
import com.proyects.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {
	
	private final ProductRepository productRepository ;
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public ProductDto addProduct(ProductDto productDto) throws  IOException{
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImg(productDto.getImg().getBytes());
		
		Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
		
		product.setCategory(category);
		return productRepository.save(product).getDto();
	}
	
	public List<ProductDto> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}

}
