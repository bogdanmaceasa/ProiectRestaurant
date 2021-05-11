package com.siit.finalproject.restaurant.mapper;

import com.siit.finalproject.restaurant.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurant.model.entities.RestaurantsEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Builder
@RequiredArgsConstructor
@Component
public class MapperForGetRestaurants {

    public RestaurantGetDTO mapEntityToGetDTO(RestaurantsEntity restaurantsEntity) {
        return RestaurantGetDTO.builder()
                .id(restaurantsEntity.getId())
                .name(restaurantsEntity.getName())
                .address(restaurantsEntity.getAddress().getStreet())
                .city(restaurantsEntity.getAddress().getCity())
                .specialities(restaurantsEntity.getSpecialitiesSet()
                        .stream()
                        .map(s -> s.getType())
                        .collect(Collectors.toSet()))
                .details(restaurantsEntity.getDetails().getDetails())
                .build();
    }


}
