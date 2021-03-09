package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.booking.Booking;
import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.*;
import com.siit.finalproject.restaurantEntries.repository.AddressRepository;
import com.siit.finalproject.restaurantEntries.repository.DetailsRepository;
import com.siit.finalproject.restaurantEntries.repository.SpecialitiesRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Builder
@RequiredArgsConstructor
@Component
public class MapperForPostRestaurants {

    private final AddressRepository addressRepository;
    private final DetailsRepository detailsRepository;
    private final SpecialitiesRepository specialitiesRepository;
    private final BookingRepository bookingRepository;

    public RestaurantsEntity mapPostDTOToEntity(RestaurantPostDTO restaurantPostDTO) {
        return RestaurantsEntity.builder()
                .id(restaurantPostDTO.getId())
                .name(restaurantPostDTO.getName())
                .address(addressRepository.findById(restaurantPostDTO.getAddressId()).orElseThrow())
                .specialities(specialitiesRepository.findById(restaurantPostDTO.getSpecialitiesId()).orElseThrow())
                .details(detailsRepository.findById(restaurantPostDTO.getDetailsId()).orElseThrow())
                .booking(bookingRepository.findById(restaurantPostDTO.getBookingId()).orElseThrow())
                .build();
    }
}
