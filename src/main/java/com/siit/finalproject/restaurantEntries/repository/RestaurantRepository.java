package com.siit.finalproject.restaurantEntries.repository;

import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantsEntity,Integer> {


    RestaurantsEntity findByName(String name);

}
