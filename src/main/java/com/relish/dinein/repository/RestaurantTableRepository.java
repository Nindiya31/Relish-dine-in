package com.relish.dinein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.relish.dinein.entity.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}
