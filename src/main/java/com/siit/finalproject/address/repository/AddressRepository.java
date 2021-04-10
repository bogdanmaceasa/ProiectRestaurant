package com.siit.finalproject.address.repository;


import com.siit.finalproject.address.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {


    Optional<List<AddressEntity>> findByCity(String cityName);
    Optional<AddressEntity> findByCityAndStreet(String city, String street);
}
