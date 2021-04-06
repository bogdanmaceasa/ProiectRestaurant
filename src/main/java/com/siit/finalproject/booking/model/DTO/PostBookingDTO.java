package com.siit.finalproject.booking.model.DTO;

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


    private LocalDateTime bookingDate = LocalDateTime.now(ZoneOffset.ofHours(2));

    private Integer restaurantId;

    private Integer userId;


}
