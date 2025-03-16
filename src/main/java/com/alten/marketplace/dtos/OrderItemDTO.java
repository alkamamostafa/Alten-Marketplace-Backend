package com.alten.marketplace.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderItemDTO {
	
    private Long id;
    private Long productId;
    private Integer quantity;
    
}
