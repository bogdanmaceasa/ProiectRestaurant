package com.siit.finalproject.booking.model.DTO;

import com.siit.finalproject.restaurant.model.DTO.RestaurantGetDTO;
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
public class GetUserBookingsDTO {

    private Integer id;

    private LocalDateTime bookingDate = LocalDateTime.now(ZoneOffset.ofHours(2));

    private String status;

    private RestaurantGetDTO restaurant;

}
