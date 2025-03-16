package com.alten.marketplace.services;

import com.alten.marketplace.dtos.FavoritelistItemDTO;
import com.alten.marketplace.dtos.mapper.FavoritelistItemDTOMapper;
import com.alten.marketplace.entities.FavoriteListItem;
import com.alten.marketplace.entities.Favoritelist;
import com.alten.marketplace.entities.Product;
import com.alten.marketplace.entities.User;
import com.alten.marketplace.repositories.FavoritelistItemRepository;
import com.alten.marketplace.repositories.FavoritelistRepository;
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
public class FavoritelistServiceImpl implements IFavoritelistService {
	
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final FavoritelistRepository favoritelistRepository;
	private final FavoritelistItemRepository favoritelistItemRepository;
	
	private final FavoritelistItemDTOMapper favoritelistItemDTOMapper;

	@Override
	public List<FavoritelistItemDTO> getAllFavoritelistItemsByUserEmail(Principal principal) throws Exception {
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new Exception("can't find user"));
		
		Favoritelist userFavoritelist = this.favoritelistRepository.findById(authenticatedUser.getId())
				.orElseThrow(() -> new Exception("can't find favoritelist"));
		return userFavoritelist.getFavoritelistItems().stream().map(this.favoritelistItemDTOMapper).toList();
	}

	@Override
	public FavoritelistItemDTO addToFavoritelist(Principal principal, FavoritelistItemDTO favoritelistItemDTO) throws Exception {
		
		User authenticatedUser = this.userRepository.findByEmail(principal.getName())
									.orElseThrow(() -> new Exception("can't find user"));
		
		Favoritelist favoritelistOfUser = this.favoritelistRepository.findById(authenticatedUser.getId())
									.get();
									
		Product choosenProduct = this.productRepository.findById(favoritelistItemDTO.getProductId())
									.orElseThrow(() -> new Exception("can't find product"));
		
		FavoriteListItem favoritelistItemToAdd = FavoriteListItem.builder()
									.product(choosenProduct)
									.favoritelist(favoritelistOfUser)
									.build();
		
		favoritelistOfUser.addFavoritelistItem(favoritelistItemToAdd);
		choosenProduct.addFavoritelistItem(favoritelistItemToAdd);
								
		
		return Optional.of(this.favoritelistItemRepository.save(favoritelistItemToAdd))
				.map(this.favoritelistItemDTOMapper)
				.orElseThrow(() -> new Exception("error creating the favoritelistItem in database"));
	}

	@Override
	public FavoritelistItemDTO removeFromFavoritelist(Principal principal, Long favoritelistItemId) throws Exception {
		FavoritelistItemDTO favoritelistItemToDeleteDTO = this.favoritelistItemRepository.findById(favoritelistItemId)
				.map(this.favoritelistItemDTOMapper)
				.orElseThrow(() -> new Exception("can't find favoritelistItem"));
		
		this.favoritelistItemRepository.deleteById(favoritelistItemToDeleteDTO.getId());
		
		return favoritelistItemToDeleteDTO;
	}

}
