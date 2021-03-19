package com.siit.finalproject.restaurantEntries.repository;


import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantSpecialitiesEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.SpecialitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RestaurantSpecialitiesRepository extends JpaRepository<RestaurantSpecialitiesEntity,Integer> {


    @Query(value = "select * from project.restaurantspecialities where speciality_id = ?",
            nativeQuery = true)
    RestaurantSpecialitiesEntity findSpecialitiesBySpecialityId(Integer id);

    int findAllBySpecialitiesEntityId(Integer id);


}
