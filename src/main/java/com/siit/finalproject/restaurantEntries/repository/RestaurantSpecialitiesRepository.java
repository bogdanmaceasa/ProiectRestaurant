package com.siit.finalproject.restaurantEntries.repository;


import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantSpecialitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantSpecialitiesRepository extends JpaRepository<RestaurantSpecialitiesEntity, Integer> {

}
