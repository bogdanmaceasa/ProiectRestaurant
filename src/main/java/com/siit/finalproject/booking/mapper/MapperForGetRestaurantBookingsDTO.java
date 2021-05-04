package com.siit.finalproject.booking.mapper;

import com.siit.finalproject.booking.model.DTO.GetRestaurantBookingsDTO;
import com.siit.finalproject.booking.model.Entities.BookingEntity;
import com.siit.finalproject.restaurant.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurant.service.RestaurantsService;
import com.siit.finalproject.userAccounts.mapper.UserEntityToDTOMap;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@RequiredArgsConstructor
@Component
public class MapperForGetRestaurantBookingsDTO {

    private final MapperForGetRestaurants mapperForGetRestaurants;
    private final UserEntityToDTOMap mapperForGetEntityToDTO;
    private final RestaurantsService restaurantsService;

    public GetRestaurantBookingsDTO mapEntityToGetRestaurantBookingDTO(BookingEntity bookingEntity) {
        return GetRestaurantBookingsDTO.builder()
                .id(bookingEntity.getId())
                .bookingDate(bookingEntity.getBookingDate())
                .status(bookingEntity.getStatus())
                .user(mapperForGetEntityToDTO.mapFromEntityToDTO(bookingEntity.getUserId()))
                .build();
    }

}
