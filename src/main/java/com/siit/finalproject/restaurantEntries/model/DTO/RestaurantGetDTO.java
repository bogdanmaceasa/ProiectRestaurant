package com.siit.finalproject.restaurantEntries.model.DTO;

import com.siit.finalproject.address.model.entity.AddressEntity;
import com.siit.finalproject.details.model.Entity.DetailsEntity;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantGetDTO {

    private int id;

    private String name;

    private Set<String> specialities;

    private String details;

    private String city;

    private String address;

//    private Set<BookingEntity> bookings;

}
