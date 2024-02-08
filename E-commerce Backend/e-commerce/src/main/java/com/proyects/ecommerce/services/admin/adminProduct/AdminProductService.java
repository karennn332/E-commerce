package com.proyects.ecommerce.services.admin.adminProduct;

import java.io.IOException;
import java.util.List;

import com.proyects.ecommerce.dto.ProductDto;

public interface AdminProductService {
	
	ProductDto addProduct(ProductDto productDto) throws IOException;
	
	List<ProductDto> getAllProducts();

}
