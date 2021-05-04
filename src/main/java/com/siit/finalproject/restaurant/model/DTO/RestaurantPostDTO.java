package com.siit.finalproject.restaurant.model.DTO;

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
    private String city;

    @NotNull
    private String address;

    @NotNull
    private Set<String> specialities;

    private String detailsInput;

}
