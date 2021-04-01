package com.siit.finalproject.booking.DTO;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostBookingDTO {


    private LocalDateTime bookingDate=LocalDateTime.now(ZoneOffset.ofHours(2));

    private Integer restaurantId;

    private Integer userId;


}
