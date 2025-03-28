package com.alten.marketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteListItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "favoritelistId")
	private Favoritelist favoritelist;


	@Override
	public int hashCode() {
		return 2025;
	}
	
	
}
