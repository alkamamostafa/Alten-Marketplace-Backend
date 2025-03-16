package com.alten.marketplace.entities.mapper;

import com.alten.marketplace.dtos.ProductFormDTO;
import com.alten.marketplace.entities.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductEntityMapper implements Function<ProductFormDTO, Product> {

	@Override
	public Product apply(ProductFormDTO productFormDto) {
		return Product.builder()
				.id(productFormDto.getId())
				.code(productFormDto.getCode())
				.name(productFormDto.getName())
				.description(productFormDto.getDescription())
				.image(productFormDto.getImage())
				.category(productFormDto.getCategory())
				.price(productFormDto.getPrice())
				.quantity(productFormDto.getQuantity())
				.internalReference(productFormDto.getInternalReference())
				.shellId(productFormDto.getShellId())
				.inventoryStatus(productFormDto.getInventoryStatus())
				.rating(productFormDto.getRating())
				.build();
	}

}
