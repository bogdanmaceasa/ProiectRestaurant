package com.siit.finalproject.restaurantEntries.repository;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantsEntity,Integer> {
// Used SELECT QUERIES!!!!!!!!


    List<RestaurantPostDTO> findAllByNameIsContaining(String name);

}
