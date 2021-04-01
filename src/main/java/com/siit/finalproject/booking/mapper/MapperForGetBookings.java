package com.siit.finalproject.booking.mapper;

import com.siit.finalproject.booking.DTO.GetBookingDTO;
import com.siit.finalproject.booking.model.BookingEntity;
import com.siit.finalproject.restaurantEntries.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
import com.siit.finalproject.userAccounts.mapper.UserEntityToDTOMap;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@RequiredArgsConstructor
@Component
public class MapperForGetBookings {

    private final MapperForGetRestaurants mapperForGetRestaurants;
    private final UserEntityToDTOMap mapperForGetEntityToDTO;
    private final RestaurantsService restaurantsService;

    public GetBookingDTO mapperForGetBookingsEntityToDTO (BookingEntity bookingEntity){

        return GetBookingDTO.builder()
                .id(bookingEntity.getId())
                .bookingDate(bookingEntity.getBookingDate())
                .status(bookingEntity.getStatus())
                .restaurant(mapperForGetRestaurants.mapEntityToGetDTO(bookingEntity.getRestaurantId()))
                .user(mapperForGetEntityToDTO.mapFromEntityToDTO(bookingEntity.getUserId()))
                .build();
    }

}
