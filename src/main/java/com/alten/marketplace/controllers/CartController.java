package com.alten.marketplace.controllers;


import com.alten.marketplace.dtos.OrderItemDTO;
import com.alten.marketplace.services.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;



@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
	
	private final ICartService cartService;
	
	@GetMapping
	public ResponseEntity<List<OrderItemDTO>> getAllCartItemsForUser(Principal principal) throws Exception {
		List<OrderItemDTO> cartItemsList = this.cartService.getAllOrderItemsByUserEmail(principal);
		return new ResponseEntity<>(cartItemsList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderItemDTO> addToCart(Principal principal, @RequestBody OrderItemDTO cartItemDTO) throws Exception {
		OrderItemDTO addedCartItem = this.cartService.addToCart(principal, cartItemDTO);
		return new ResponseEntity<>(addedCartItem, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{cartId}")
	public ResponseEntity<OrderItemDTO> removeFromCart(Principal principal, @PathVariable Long cartId) throws Exception {
		OrderItemDTO addedCartItem = this.cartService.removeFromCart(principal, cartId);
		return new ResponseEntity<>(addedCartItem, HttpStatus.OK);
	}

}
