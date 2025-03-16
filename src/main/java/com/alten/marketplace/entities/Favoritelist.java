package com.alten.marketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favoritelist implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    private Long id;
    
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "favoritelist", orphanRemoval = true)
	private List<FavoriteListItem> favoritelistItems;


	@Override
	public int hashCode() {
		return 2025;
	}

    public void addFavoritelistItem(FavoriteListItem favoritelistItem){
    	this.favoritelistItems.add(favoritelistItem);
    	favoritelistItem.setFavoritelist(this);
    }

	public void removeFavoritelistItem(FavoriteListItem favoritelistItem) {
		favoritelistItem.setFavoritelist(null);
    	this.favoritelistItems.remove(favoritelistItem);
    }
    
    public void removeFavoritelistItems() {
    	Iterator<FavoriteListItem> iterator = this.favoritelistItems.iterator();
    	while (iterator.hasNext()) {
			FavoriteListItem favoritelistItem = iterator.next();
			favoritelistItem.setFavoritelist(null);
			iterator.remove();
		}
    }
    
}
