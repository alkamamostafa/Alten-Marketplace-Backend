package com.alten.marketplace.controllers;

import com.alten.marketplace.dtos.ProductDTO;
import com.alten.marketplace.dtos.ProductFeedbackDTO;
import com.alten.marketplace.dtos.ProductFormDTO;
import com.alten.marketplace.services.IProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final IProductService productService;
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) throws Exception {
		Page<ProductDTO> productsDtoPage = this.productService.getAllProducts(pageable);
		return new ResponseEntity<>(productsDtoPage, HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) throws Exception {
		ProductDTO productDto = this.productService.getProductById(productId);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("authentication.principal.claims['email'] == 'admin@admin.com'")
	public ResponseEntity<ProductFeedbackDTO> createProduct(@RequestBody ProductFormDTO productToCreate) throws Exception {
		ProductFeedbackDTO createdProductFeedbackDto = this.productService.createProduct(productToCreate);

		return new ResponseEntity<>(createdProductFeedbackDto, HttpStatus.CREATED);
	}
	
	@PatchMapping("/{productId}")
	@PreAuthorize("authentication.principal.claims['email'] == 'admin@admin.com'")

	public ResponseEntity<ProductFeedbackDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductFormDTO productToUpdate) throws Exception {
		productToUpdate.setId(productId);
		ProductFeedbackDTO updatedProductFeedbackDto = this.productService.updateProduct(productToUpdate);

		return new ResponseEntity<>(updatedProductFeedbackDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	@PreAuthorize("authentication.principal.claims['email'] == 'admin@admin.com'")
	public ResponseEntity<ProductFeedbackDTO> deleteProduct(@PathVariable Long productId) throws Exception {
		ProductFeedbackDTO deletedProductFeedbackDto = this.productService.deleteProduct(productId);

		return new ResponseEntity<>(deletedProductFeedbackDto, HttpStatus.OK);
	}
	
	
}
