package com.siit.finalproject.booking.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetBookingDTO {


    private Integer id;

    private LocalDateTime bookingDate;

    private String status;

    private RestaurantGetDTO restaurant;

    private UserDTO user;

}
