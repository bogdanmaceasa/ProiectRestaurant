package com.siit.finalproject.address.service;

import com.siit.finalproject.address.model.entity.AddressEntity;
import com.siit.finalproject.address.repository.AddressRepository;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public boolean checkIfAddressExists(RestaurantPostDTO restaurantPostDTO, RestaurantsEntity restaurantsEntity) {

        Optional<AddressEntity> addressEntity = addressRepository.findByCityAndStreet(restaurantPostDTO.getCity(), restaurantPostDTO.getAddress());

        return addressEntity.filter(entity -> !restaurantsEntity.getAddress().equals(entity)).isPresent();

    }


}
