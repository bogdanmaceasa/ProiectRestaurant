package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
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
                .address(restaurantsEntity.getAddress())
                .specialities(restaurantsEntity.getSpecialitiesSet()
                        .stream()
                        .map(s -> s.getType())
                        .collect(Collectors.toSet()))
                .details(restaurantsEntity.getDetails())
                .build();
    }


}
