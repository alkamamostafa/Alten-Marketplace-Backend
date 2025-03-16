package com.alten.marketplace.dtos.mapper;

import com.alten.marketplace.dtos.ProductDTO;
import com.alten.marketplace.entities.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

	@Override
	public ProductDTO apply(Product product) {
		return ProductDTO.builder()
				.id(product.getId())
				.code(product.getCode())
				.name(product.getName())
				.description(product.getDescription())
				.image(product.getImage())
				.price(product.getPrice())
				.category(product.getCategory())
				.quantity(product.getQuantity())
				.internalReference(product.getInternalReference())
				.shellId(product.getShellId())
				.inventoryStatus(product.getInventoryStatus())
				.rating(product.getRating())
				.createdAt(product.getCreatedAt())
				.updatedAt(product.getUpdatedAt())
				.build();
	}

}
