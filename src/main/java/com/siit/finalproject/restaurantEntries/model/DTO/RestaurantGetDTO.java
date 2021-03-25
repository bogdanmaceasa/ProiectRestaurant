package com.siit.finalproject.restaurantEntries.model.DTO;

import com.siit.finalproject.booking.Booking;
import com.siit.finalproject.restaurantEntries.model.Entities.AddressEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.DetailsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantSpecialitiesEntity;
import com.siit.finalproject.specialities.model.DTO.SpecialitiesDTO;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantGetDTO {

    private int id;

    private String name;

    private Set<RestaurantSpecialitiesEntity> specialities;

//    private Set<String> specialities;

//    private Set<SpecialitiesDTO> specialities;

    private DetailsEntity details;

    private Booking booking;

    private AddressEntity address;


}
