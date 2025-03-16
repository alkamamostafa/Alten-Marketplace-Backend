package com.alten.marketplace.services;

import com.alten.marketplace.dtos.OrderItemDTO;
import com.alten.marketplace.dtos.mapper.OrderItemDTOMapper;
import com.alten.marketplace.entities.Cart;
import com.alten.marketplace.entities.OrderItem;
import com.alten.marketplace.entities.Product;
import com.alten.marketplace.entities.User;
import com.alten.marketplace.repositories.CartRepository;
import com.alten.marketplace.repositories.OrderItemRepository;
import com.alten.marketplace.repositories.ProductRepository;
import com.alten.marketplace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
	
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final OrderItemRepository cartItemRepository;
	
	private final OrderItemDTOMapper cartItemDTOMapper;

	@Override
	public List<OrderItemDTO> getAllOrderItemsByUserEmail(Principal principal) throws Exception {
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new Exception("can't find user"));
		
		Cart userCart = this.cartRepository.findById(authenticatedUser.getId())
				.orElseThrow(() -> new Exception("can't find cart"));
		return userCart.getCartItems().stream().map(this.cartItemDTOMapper).toList();
	}

	public OrderItemDTO addToCart(Principal principal, OrderItemDTO cartItemDTO) throws Exception {
		
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
									.orElseThrow(() -> new Exception("can't find user"));
		
		Cart cartOfUser = this.cartRepository.findById(authenticatedUser.getId())
									.orElseThrow(() -> new Exception("can't find cart"));
									
		Product choosenProduct = this.productRepository.findById(cartItemDTO.getProductId())
									.orElseThrow(() -> new Exception("can't find product"));
		
		OrderItem cartItemToAdd = OrderItem.builder()
									.product(choosenProduct)
									.cart(cartOfUser)
									.quantity(cartItemDTO.getQuantity())
									.build();
		
		cartOfUser.addOrderItem(cartItemToAdd);
		choosenProduct.addfavorite(cartItemToAdd);
								
		
		return Optional.of(this.cartItemRepository.save(cartItemToAdd))
				.map(this.cartItemDTOMapper)
				.orElseThrow(() -> new Exception("error creating the cartItem in database"));
	}

	@Override
	public OrderItemDTO removeFromCart(Principal principal, Long cartItemId) throws Exception {
		OrderItemDTO cartItemToDeleteDTO = this.cartItemRepository.findById(cartItemId)
				.map(this.cartItemDTOMapper)
				.orElseThrow(() -> new Exception("can't find cartItem"));
		
		this.cartItemRepository.deleteById(cartItemToDeleteDTO.getId());
		
		return cartItemToDeleteDTO;
	}

}
