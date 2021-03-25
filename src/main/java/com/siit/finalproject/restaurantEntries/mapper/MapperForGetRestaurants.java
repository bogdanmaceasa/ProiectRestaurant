package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.specialities.mapper.MapperForGetSpecialities;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import com.siit.finalproject.specialities.service.SpecialitiesService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Builder
@RequiredArgsConstructor
@Component
public class MapperForGetRestaurants {

    private final SpecialitiesService specialitiesService;
    private final SpecialitiesRepository specialitiesRepository;
    private final MapperForGetSpecialities mapperForGetSpecialities;

    public RestaurantGetDTO mapEntityToGetDTO(RestaurantsEntity restaurantsEntity) {
        return RestaurantGetDTO.builder()
                .id(restaurantsEntity.getId())
                .name(restaurantsEntity.getName())
                .address(restaurantsEntity.getAddress())
                .specialities(restaurantsEntity.getSpecialities())
//                .specialities(restaurantsEntity.getSpecialities().stream()
//                        .map(s -> s.getSpecialityId())
//                        .map(s -> specialitiesRepository.findTypeById(s))
//                        .map(s-> mapperForGetSpecialities.mapSpecialitiesEntityToGetDTO(s))
//                        .collect(Collectors.toSet()))
                .details(restaurantsEntity.getDetails())
                .booking(restaurantsEntity.getBooking())
                .build();
    }

}
