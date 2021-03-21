package com.siit.finalproject.restaurantEntries.repository;


import com.siit.finalproject.restaurantEntries.model.Entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
