package com.spring.order_inventory.repository;

import com.spring.order_inventory.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {}