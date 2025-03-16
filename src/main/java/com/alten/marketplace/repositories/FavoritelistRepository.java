package com.alten.marketplace.repositories;

import com.alten.marketplace.entities.Favoritelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritelistRepository extends JpaRepository<Favoritelist, Long> {

}
