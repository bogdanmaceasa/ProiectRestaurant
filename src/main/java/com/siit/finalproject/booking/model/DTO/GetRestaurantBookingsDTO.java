package com.siit.finalproject.booking.model.DTO;

import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetRestaurantBookingsDTO {

    private Integer id;

    private LocalDateTime bookingDate;

    private String status;

    private UserDTO user;

}
