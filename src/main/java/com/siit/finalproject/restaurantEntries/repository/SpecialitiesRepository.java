package com.siit.finalproject.restaurantEntries.repository;


import com.siit.finalproject.restaurantEntries.model.Entities.SpecialitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialitiesRepository extends JpaRepository<SpecialitiesEntity,Integer> {
}
