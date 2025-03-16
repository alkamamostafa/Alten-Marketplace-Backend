package com.alten.marketplace.services;

import com.alten.marketplace.dtos.mapper.ProductDTOMapper;
import com.alten.marketplace.dtos.mapper.ProductFeedbackDTOMapper;
import com.alten.marketplace.entities.Product;
import com.alten.marketplace.dtos.ProductDTO;
import com.alten.marketplace.dtos.ProductFeedbackDTO;
import com.alten.marketplace.dtos.ProductFormDTO;
import com.alten.marketplace.entities.mapper.ProductEntityMapper;
import com.alten.marketplace.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;
	
	private final ProductEntityMapper productEntityMapper;
	private final ProductDTOMapper productDTOMapper;
	private final ProductFeedbackDTOMapper productFeedbackDTOMapper;
	
	@Override
	public Page<ProductDTO> getAllProducts(Pageable pageable) throws Exception {
		Page<Product> productsPage = this.productRepository.findAll(pageable);
		return productsPage.map(this.productDTOMapper);
	}

	@Override
	public ProductDTO getProductById(Long productId) throws Exception {
		return this.productRepository
				.findById(productId)
				.map(this.productDTOMapper)
				.orElseThrow(() -> new Exception("product not found"));
	}

	@Override
	public ProductFeedbackDTO createProduct(ProductFormDTO productFormDTO) throws Exception {
		return Optional.of(productFormDTO)
				.map(this.productEntityMapper)
				.map(this.productRepository::save)
				.map(this.productFeedbackDTOMapper)
				.orElseThrow(() -> new Exception("Problem: product not created in database"));
	}

	@Override
	public ProductFeedbackDTO updateProduct(ProductFormDTO productFormDTO) throws Exception {
		if(!this.productRepository.existsById(productFormDTO.getId()))
			throw new Exception("Problem during Product Update: product does not exist");
		
		return Optional.of(productFormDTO)
				.map(this.productEntityMapper)
				.map(this.productRepository::save)
				.map(this.productFeedbackDTOMapper)
				.orElseThrow(() -> new Exception("Problem: product not updated in database"));
	}

	@Override
	public ProductFeedbackDTO deleteProduct(Long productId) throws Exception {
		Product productToDelete = this.productRepository.findById(productId)
				.orElseThrow(() -> new Exception("Problem during Product Delete: product does not exist"));
		
		ProductFeedbackDTO productFeedbackDTO = Optional.of(productToDelete)
													.map(this.productFeedbackDTOMapper)
													.orElseThrow(() -> new Exception("productFeedbackDTOMapper Error"));
		
		
		this.productRepository.delete(productToDelete);
		return productFeedbackDTO;
	}

}
