package com.siit.finalproject.restaurantEntries.model.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompositeKey implements Serializable {

    private Integer restaurantId;

    private Integer specialityId;

}
