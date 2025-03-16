package com.alten.marketplace.services;



import com.alten.marketplace.dtos.FavoritelistItemDTO;

import java.security.Principal;
import java.util.List;


public interface IFavoritelistService {
	List<FavoritelistItemDTO> getAllFavoritelistItemsByUserEmail(Principal principal) throws Exception;
	
	FavoritelistItemDTO addToFavoritelist(Principal principal, FavoritelistItemDTO favoritelistItemDTO)  throws Exception;
	
	FavoritelistItemDTO removeFromFavoritelist(Principal principal, Long favoritelistItemId)  throws Exception;
}
