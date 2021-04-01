package com.siit.finalproject.booking.DTO;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetUserBookingsDTO {

    private Integer id;

    private LocalDateTime bookingDate= LocalDateTime.now(ZoneOffset.ofHours(2));

    private String status;

    private RestaurantGetDTO restaurant;

}
