package com.siit.finalproject.restaurantEntries.repository;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsJoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantJoinRepository extends JpaRepository<RestaurantsJoinEntity,Integer> {

}
