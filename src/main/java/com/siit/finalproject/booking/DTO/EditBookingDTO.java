package com.siit.finalproject.booking.DTO;

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
public class EditBookingDTO {


    private Integer id;

    private LocalDateTime bookingDate;

    private String status;

    private Integer restaurantId;

    private Integer userId;


}
