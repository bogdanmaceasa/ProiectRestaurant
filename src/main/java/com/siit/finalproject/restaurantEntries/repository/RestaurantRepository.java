package com.siit.finalproject.restaurantEntries.repository;

import com.siit.finalproject.address.model.entity.AddressEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantsEntity, Integer> {


    Optional<RestaurantsEntity> findByName(String name);

    Optional<RestaurantsEntity> findByAddress(AddressEntity addressEntity);

}
