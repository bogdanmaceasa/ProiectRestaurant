package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.*;
import com.siit.finalproject.restaurantEntries.repository.*;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Builder
@RequiredArgsConstructor
@Component
public class MapperForPostRestaurants {

    private final AddressRepository addressRepository;
    private final DetailsRepository detailsRepository;
    private final SpecialitiesRepository specialitiesRepository;
    private final BookingRepository bookingRepository;
    private final RestaurantRepository restaurantRepository;

    // mapperForPostRestaurants DOES NOT IGNORE the ID that is passed by the POST Method
    public RestaurantsEntity mapPostDTOToEntity(RestaurantPostDTO restaurantPostDTO) {
        RestaurantsEntity rest = RestaurantsEntity.builder()
                                                .id(restaurantPostDTO.getId())
                                                .name(restaurantPostDTO.getName())
                                                .address(addressRepository.findById(restaurantPostDTO.getAddressId()).get())
                                                .details(detailsRepository.findById(restaurantPostDTO.getDetailsId()).get())
                                                .booking(bookingRepository.findById(restaurantPostDTO.getBookingId()).get())
                                                .build();
        return rest;
    }

    public void mapPostDTOToEntitySpecialities(RestaurantPostDTO restaurantPostDTO) {

        restaurantPostDTO.getSpecialities().stream()
                .map(s-> specialitiesRepository.findById(s).get())
                .forEach(s-> restaurantRepository.findById(restaurantPostDTO.getId()).get().addSpeciality(s));
    }



}
