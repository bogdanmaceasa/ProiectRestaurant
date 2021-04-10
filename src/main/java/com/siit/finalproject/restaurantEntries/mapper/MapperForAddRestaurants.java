package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.details.detailsTextProcessor.InputTextToFile;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.details.model.Entity.DetailsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.address.repository.AddressRepository;
import com.siit.finalproject.details.repository.DetailsRepository;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Builder
@RequiredArgsConstructor
@Component
public class MapperForAddRestaurants {

    private final AddressRepository addressRepository;
    private final DetailsRepository detailsRepository;
    private final SpecialitiesRepository specialitiesRepository;
    private final BookingRepository bookingRepository;
    private final RestaurantRepository restaurantRepository;

    public RestaurantsEntity mapAddDTOToEntity(RestaurantPostDTO restaurantPostDTO) {

        String details = InputTextToFile.createOrUpdateFile(restaurantPostDTO.getName(), restaurantPostDTO.getDetailsInput());


        RestaurantsEntity rest = RestaurantsEntity.builder()
                .name(restaurantPostDTO.getName())
                .address(addressRepository.findById(restaurantPostDTO.getAddressId()).get())
                .specialitiesSet(restaurantPostDTO.getSpecialities().stream()
                        .map(s -> specialitiesRepository.findById(s).get())
                        .collect(Collectors.toSet()))
                .details(detailsRepository.findDetailsEntityByDetails(details).orElse(detailsRepository.save(DetailsEntity.builder().details(details).build())))
                .build();
        return rest;
    }
}
