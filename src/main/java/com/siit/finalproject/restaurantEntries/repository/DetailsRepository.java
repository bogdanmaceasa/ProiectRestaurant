package com.siit.finalproject.restaurantEntries.repository;


import com.siit.finalproject.restaurantEntries.model.Entities.DetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<DetailsEntity,Integer> {
}
