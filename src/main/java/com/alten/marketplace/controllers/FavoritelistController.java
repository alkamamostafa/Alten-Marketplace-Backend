package com.alten.marketplace.controllers;

import com.alten.marketplace.dtos.FavoritelistItemDTO;
import com.alten.marketplace.services.IFavoritelistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoritelistController {
	
	private final IFavoritelistService favoritelistService;
	
	@GetMapping
	public ResponseEntity<List<FavoritelistItemDTO>> getAllFavoritelistItemsForUser(Principal principal) throws Exception {
		List<FavoritelistItemDTO> wishlistItemsList = this.favoritelistService.getAllFavoritelistItemsByUserEmail(principal);
		return new ResponseEntity<>(wishlistItemsList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<FavoritelistItemDTO> addToFavoritelist(Principal principal, @RequestBody FavoritelistItemDTO wishlistItemDTO) throws Exception {
		FavoritelistItemDTO addedFavoritelistItem = this.favoritelistService.addToFavoritelist(principal, wishlistItemDTO);
		return new ResponseEntity<>(addedFavoritelistItem, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{wishlistItemId}")
	public ResponseEntity<FavoritelistItemDTO> removeFromFavoritelist(Principal principal, @PathVariable Long wishlistItemId) throws Exception {
		FavoritelistItemDTO addedFavoritelistItem = this.favoritelistService.removeFromFavoritelist(principal, wishlistItemId);
		return new ResponseEntity<>(addedFavoritelistItem, HttpStatus.OK);
	}

}
