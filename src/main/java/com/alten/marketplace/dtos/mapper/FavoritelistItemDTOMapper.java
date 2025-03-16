package com.alten.marketplace.dtos.mapper;

import com.alten.marketplace.dtos.FavoritelistItemDTO;
import com.alten.marketplace.entities.FavoriteListItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FavoritelistItemDTOMapper implements Function<FavoriteListItem, FavoritelistItemDTO>{

	@Override
	public FavoritelistItemDTO apply(FavoriteListItem favoritelistItem) {
		return FavoritelistItemDTO.builder()
				.id(favoritelistItem.getId())
				.productId(favoritelistItem.getProduct().getId())
				.build();
	}

}
