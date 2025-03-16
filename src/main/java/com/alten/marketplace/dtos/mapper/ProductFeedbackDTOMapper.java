package com.alten.marketplace.dtos.mapper;

import com.alten.marketplace.dtos.ProductFeedbackDTO;
import com.alten.marketplace.entities.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductFeedbackDTOMapper implements Function<Product, ProductFeedbackDTO> {

	@Override
	public ProductFeedbackDTO apply(Product product) {
		return ProductFeedbackDTO.builder()
				.id(product.getId())
				.code(product.getCode())
				.name(product.getName())
				.description(product.getDescription())
				.image(product.getImage())
				.build();
	}

}
