package com.alten.marketplace.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FavoritelistItemDTO {
	
    private Long id;
    private Long productId;
    
}
