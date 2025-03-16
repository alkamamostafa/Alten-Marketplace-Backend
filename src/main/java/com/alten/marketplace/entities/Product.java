package com.alten.marketplace.entities;

import com.alten.marketplace.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable{
	
    private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String image;
    
    @Column(nullable = false)
    private String category;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    private Integer quantity;
    
    private String internalReference;
    
    private Long shellId;
    
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    
    private Integer rating;
    
    @CreatedDate
    private Long createdAt;
    
    @LastModifiedDate
    private Long updatedAt;
    
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "product", orphanRemoval = true)
	private List<OrderItem> orderItems;
    
	@OneToMany(cascade = CascadeType.ALL, 
			mappedBy = "product", orphanRemoval = true)
	private List<FavoriteListItem> favoriteListItems;

	@Override
	public int hashCode() {
		return 2025;
	}
	
	
	 public void addfavorite(OrderItem cartItem){
    	this.orderItems.add(cartItem);
    	cartItem.setProduct(this);
    }

	public void removefavorite(OrderItem cartItem) {
		cartItem.setProduct(null);
    	this.orderItems.remove(cartItem);
    }
    
    public void removefavorites() {
    	Iterator<OrderItem> iterator = this.orderItems.iterator();
    	while (iterator.hasNext()) {
			OrderItem cartItem = iterator.next();
			cartItem.setProduct(null);
			iterator.remove();
		}
    }
    

    public void addFavoritelistItem(FavoriteListItem favoritelistItem){
    	this.favoriteListItems.add(favoritelistItem);
    	favoritelistItem.setProduct(this);
    }

	public void removeFavoritelistItem(FavoriteListItem favoritelistItem) {
		favoritelistItem.setProduct(null);
    	this.favoriteListItems.remove(favoritelistItem);
    }
    
    public void removeFavoritelistItems() {
    	Iterator<FavoriteListItem> iterator = this.favoriteListItems.iterator();
    	while (iterator.hasNext()) {
			FavoriteListItem favoritelistItem = iterator.next();
			favoritelistItem.setProduct(null);
			iterator.remove();
		}
    }
}
