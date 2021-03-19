package com.siit.finalproject.restaurantEntries.model.DTO;

import com.siit.finalproject.booking.Booking;
import com.siit.finalproject.restaurantEntries.model.Entities.AddressEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.DetailsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.SpecialitiesEntity;
import com.sun.istack.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantPostDTO implements Serializable {

    private int id;

    private String name;

    @NotNull
    private int addressId;

    @NotNull
    private HashSet<Integer> specialities;

    @NotNull
    private int detailsId;

    @NotNull
    private int bookingId;

}
