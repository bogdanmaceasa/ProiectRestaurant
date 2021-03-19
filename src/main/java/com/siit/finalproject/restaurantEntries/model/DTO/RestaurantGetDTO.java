package com.siit.finalproject.restaurantEntries.model.DTO;

import com.siit.finalproject.booking.Booking;
import com.siit.finalproject.restaurantEntries.model.Entities.AddressEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.DetailsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantSpecialitiesEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.SpecialitiesEntity;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantGetDTO {

    private int id;

    private String name;

    private HashSet<RestaurantSpecialitiesEntity> specialities;

    private DetailsEntity details;

    private Booking booking;

    private AddressEntity address;



}
