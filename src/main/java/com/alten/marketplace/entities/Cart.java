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
public class Cart implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    private Long id;
    
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
	private List<OrderItem> cartItems;
	
	    public void addOrderItem(OrderItem cartItem){
    	this.cartItems.add(cartItem);
    	cartItem.setCart(this);
    }

	public void removeOrderItem(OrderItem cartItem) {
		cartItem.setCart(null);
    	this.cartItems.remove(cartItem);
    }
    
    public void removeOrderItems() {
    	Iterator<OrderItem> iterator = this.cartItems.iterator();
    	while (iterator.hasNext()) {
			OrderItem cartItem = iterator.next();
			cartItem.setCart(null);
			iterator.remove();
		}
    }

	@Override
	public int hashCode() {
		return 2025;
	}
    
    
	
}
