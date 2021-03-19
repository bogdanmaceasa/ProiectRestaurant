package com.siit.finalproject.userAccounts.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    private int id;

    private String name;

    private String email;

    private int userType;


}
