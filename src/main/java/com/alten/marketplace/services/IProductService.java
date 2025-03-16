package com.alten.marketplace.services;

import com.alten.marketplace.dtos.ProductDTO;
import com.alten.marketplace.dtos.ProductFeedbackDTO;
import com.alten.marketplace.dtos.ProductFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

	Page<ProductDTO> getAllProducts(Pageable pageable) throws Exception;
	
	ProductDTO getProductById(Long productId) throws Exception;
	
	ProductFeedbackDTO createProduct(ProductFormDTO productFormDTO) throws Exception;

	ProductFeedbackDTO updateProduct(ProductFormDTO productFormDTO) throws Exception;

	ProductFeedbackDTO deleteProduct(Long productId) throws Exception;
}
