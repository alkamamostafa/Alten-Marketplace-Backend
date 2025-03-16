package com.alten.marketplace.services;


import com.alten.marketplace.dtos.OrderItemDTO;

import java.security.Principal;
import java.util.List;

public interface ICartService {
	
	List<OrderItemDTO> getAllOrderItemsByUserEmail(Principal principal) throws Exception;

	OrderItemDTO addToCart(Principal principal, OrderItemDTO cartItemDTO)  throws Exception;

	OrderItemDTO removeFromCart(Principal principal, Long cartItemId)  throws Exception;
	

}
