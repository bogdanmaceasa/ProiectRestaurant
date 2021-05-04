package com.siit.finalproject.restaurant.model.DTO;

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
