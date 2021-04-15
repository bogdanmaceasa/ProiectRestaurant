package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.address.model.entity.AddressEntity;
import com.siit.finalproject.address.repository.AddressRepository;
import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.details.detailsTextProcessor.InputTextToFile;
import com.siit.finalproject.details.model.Entity.DetailsEntity;
import com.siit.finalproject.details.repository.DetailsRepository;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.*;
import com.siit.finalproject.restaurantEntries.repository.*;
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
public class MapperForUpdateRestaurants {

    private final AddressRepository addressRepository;
    private final DetailsRepository detailsRepository;
    private final SpecialitiesRepository specialitiesRepository;
    private final BookingRepository bookingRepository;
    private final RestaurantRepository restaurantRepository;



    // mapperForPostRestaurants DOES NOT IGNORE the ID that is passed by the POST Method
    public RestaurantsEntity mapDTOToUpdateEntity(RestaurantPostDTO restaurantPostDTO) {


        String details = InputTextToFile.createOrUpdateFile(restaurantPostDTO.getName(), restaurantPostDTO.getDetailsInput());

        RestaurantsEntity rest = RestaurantsEntity.builder()
                .id(restaurantPostDTO.getId())
                .name(restaurantPostDTO.getName())
                .address(addressRepository.findByCityAndStreet(restaurantPostDTO.getCity(), restaurantPostDTO.getAddress())
                        .orElse(AddressEntity.builder()
                                .city(restaurantPostDTO.getCity())
                                .street(restaurantPostDTO.getAddress())
                                .build()))
                .details(detailsRepository.findByDetails(details)
                        .orElse(DetailsEntity.builder()
                                .details(details)
                                .build()))
                .specialitiesSet(restaurantPostDTO.getSpecialities().stream()
                        .map(s -> specialitiesRepository.findByType(s).orElse(SpecialitiesEntity.builder().type(s).build()))
                        .collect(Collectors.toSet()))

                .build();
        return rest;
    }
}
