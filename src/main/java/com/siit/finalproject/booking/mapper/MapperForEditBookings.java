package com.siit.finalproject.booking.mapper;

import com.siit.finalproject.booking.DTO.EditBookingDTO;
import com.siit.finalproject.booking.DTO.GetBookingDTO;
import com.siit.finalproject.booking.model.BookingEntity;
import com.siit.finalproject.restaurantEntries.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
import com.siit.finalproject.userAccounts.mapper.UserEntityToDTOMap;
import com.siit.finalproject.userAccounts.repository.UsersRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@RequiredArgsConstructor
@Component
public class MapperForEditBookings {

    private final RestaurantRepository restaurantRepository;
    private final UsersRepository usersRepository;

    public BookingEntity mapEditDTOToEntity (EditBookingDTO editBookingDTO){

        return BookingEntity.builder()
                .id(editBookingDTO.getId())
                .bookingDate(editBookingDTO.getBookingDate())
                .status(editBookingDTO.getStatus())
                .restaurantId(restaurantRepository.findById(editBookingDTO.getRestaurantId()).get())
                .userId(usersRepository.findById(editBookingDTO.getUserId()).get())
                .build();
    }

}
