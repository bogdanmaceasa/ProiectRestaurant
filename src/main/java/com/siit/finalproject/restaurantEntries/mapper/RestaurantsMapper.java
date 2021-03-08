package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Builder
@RequiredArgsConstructor
@Component
public class RestaurantsMapper {

    public RestaurantDTO mapRestaurantsEntityToDTO(RestaurantsEntity restaurantsEntity){
        return RestaurantDTO.builder()
                .id(restaurantsEntity.getId())
                .name(restaurantsEntity.getName())
                .address(restaurantsEntity.getAddress())
                .specialities(restaurantsEntity.getSpecialities())
                .details(restaurantsEntity.getDetails())
                .booking(restaurantsEntity.getBooking())
                .build();
    }

//
//    public RestaurantDTO mapRestaurantsEntityToDTO(RestaurantsEntity restaurantsEntity){
//        return RestaurantDTO.builder()
//                .id(restaurantsEntity.getId())
//                .name(restaurantsEntity.getName())
//                .addressId(restaurantsEntity.getAddressId())
//                .typeId(restaurantsEntity.getTypeId())
//                .detailsId(restaurantsEntity.getDetailsId())
//                .bookingId(restaurantsEntity.getBookingId())
//    }

}
