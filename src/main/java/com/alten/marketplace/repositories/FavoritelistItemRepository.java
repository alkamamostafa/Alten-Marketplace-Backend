package com.alten.marketplace.repositories;

import com.alten.marketplace.entities.FavoriteListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritelistItemRepository extends JpaRepository<FavoriteListItem, Long>{

}
