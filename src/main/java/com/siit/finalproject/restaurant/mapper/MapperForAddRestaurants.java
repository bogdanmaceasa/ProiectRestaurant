package com.siit.finalproject.restaurant.mapper;

import com.siit.finalproject.address.model.entity.AddressEntity;
import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.details.detailsTextProcessor.InputTextToFile;
import com.siit.finalproject.restaurant.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.details.model.Entity.DetailsEntity;
import com.siit.finalproject.restaurant.model.entities.RestaurantsEntity;
import com.siit.finalproject.address.repository.AddressRepository;
import com.siit.finalproject.details.repository.DetailsRepository;
import com.siit.finalproject.restaurant.repository.RestaurantRepository;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Builder
@RequiredArgsConstructor
@Component
@Transactional
public class MapperForAddRestaurants {

    private final AddressRepository addressRepository;
    private final DetailsRepository detailsRepository;
    private final SpecialitiesRepository specialitiesRepository;
    private final BookingRepository bookingRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantsEntity mapAddDTOToEntity(RestaurantPostDTO restaurantPostDTO) {

        String details = InputTextToFile.createOrUpdateFile(restaurantPostDTO.getName(),
                restaurantPostDTO.getDetailsInput());

        RestaurantsEntity rest = RestaurantsEntity.builder()
                .name(restaurantPostDTO.getName())
                .address(addressRepository.save(AddressEntity.builder()
                        .city(restaurantPostDTO.getCity())
                        .street(restaurantPostDTO.getAddress())
                        .build()))
                .specialitiesSet(restaurantPostDTO.getSpecialities().stream()
                        .map(s -> specialitiesRepository.findByType(s).orElse(SpecialitiesEntity.builder()
                                                                                                .type(s)
                                                                                                .build()))
                        .collect(Collectors.toSet()))
                .details(detailsRepository.findByDetails(details)
                        .orElse(DetailsEntity.builder()
                                .details(details)
                                .build()))
                .build();
        return rest;
    }
}
