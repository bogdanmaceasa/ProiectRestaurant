package com.siit.finalproject.address.model.DTO;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDTO {

    private String city;

    private String street;

}
