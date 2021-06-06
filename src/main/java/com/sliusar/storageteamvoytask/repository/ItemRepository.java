package com.sliusar.storageteamvoytask.repository;

import com.sliusar.storageteamvoytask.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAllByNameOrderByPriceAsc(String name);
}
