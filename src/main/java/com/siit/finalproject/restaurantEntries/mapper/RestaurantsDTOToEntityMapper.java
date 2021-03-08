package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Builder
@RequiredArgsConstructor
@Component
public class RestaurantsDTOToEntityMapper {

    public RestaurantsEntity mapRestaurantsDTOToEntity(RestaurantDTO restaurantDTO){
        return RestaurantsEntity.builder()
                .id(restaurantDTO.getId())
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .specialities(restaurantDTO.getSpecialities())
                .details(restaurantDTO.getDetails())
                .booking(restaurantDTO.getBooking())
                .build();
    }


    public RestaurantsEntity putDTOinEntity(RestaurantDTO restaurantDTO){
        return RestaurantsEntity.builder()
                .id(restaurantDTO.getId())
                .name(restaurantDTO.getName())
                .addressId(restaurantDTO.getAddressId())
                .typeId(restaurantDTO.getTypeId())
                .detailsId(restaurantDTO.getDetailsId())
                .bookingId(restaurantDTO.getBookingId())
                .build();
    }

}
