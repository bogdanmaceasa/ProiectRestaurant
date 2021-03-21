package com.siit.finalproject.specialities.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SpecialitiesDTO {

    private int id;

    private String type;
    ;

}
