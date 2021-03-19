package com.siit.finalproject.restaurantEntries.model.DTO;

import com.sun.istack.NotNull;
import lombok.*;

import java.io.Serializable;
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
    private Set<Integer> specialities;

    @NotNull
    private int detailsId;

    @NotNull
    private int bookingId;

}
