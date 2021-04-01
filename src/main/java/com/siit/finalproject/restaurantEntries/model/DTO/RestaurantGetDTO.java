package com.siit.finalproject.restaurantEntries.model.DTO;

import com.siit.finalproject.booking.model.BookingEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.AddressEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.DetailsEntity;
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

    private Set<String> specialities;

    private DetailsEntity details;

    private AddressEntity address;

//    private Set<BookingEntity> bookings;

}
