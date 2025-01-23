package com.blabla.projekat.repositories;

import com.blabla.projekat.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
