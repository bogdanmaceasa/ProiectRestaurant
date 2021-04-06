package com.siit.finalproject.booking.mapper;

import com.siit.finalproject.booking.model.DTO.GetUserBookingsDTO;
import com.siit.finalproject.booking.model.Entities.BookingEntity;
import com.siit.finalproject.restaurantEntries.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
import com.siit.finalproject.userAccounts.mapper.UserEntityToDTOMap;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@RequiredArgsConstructor
@Component
public class MapperForGetUserBookingsDTO {

    private final MapperForGetRestaurants mapperForGetRestaurants;
    private final UserEntityToDTOMap mapperForGetEntityToDTO;
    private final RestaurantsService restaurantsService;

    public GetUserBookingsDTO mapEntityToGetUserBookingsDTO(BookingEntity bookingEntity) {

        return GetUserBookingsDTO.builder()
                .id(bookingEntity.getId())
                .bookingDate(bookingEntity.getBookingDate())
                .status(bookingEntity.getStatus())
                .restaurant(mapperForGetRestaurants.mapEntityToGetDTO(bookingEntity.getRestaurantId()))
                .build();
    }

}
