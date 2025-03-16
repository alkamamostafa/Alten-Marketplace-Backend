package com.alten.marketplace.dtos.mapper;

import com.alten.marketplace.dtos.OrderItemDTO;
import com.alten.marketplace.entities.OrderItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemDTOMapper implements Function<OrderItem, OrderItemDTO> {

	@Override
	public OrderItemDTO apply(OrderItem cartItem) {
		return OrderItemDTO.builder()
				.id(cartItem.getId())
				.productId(cartItem.getProduct().getId())
				.quantity(cartItem.getQuantity())
				.build();
	}

}