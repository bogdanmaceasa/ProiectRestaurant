package com.siit.finalproject.restaurantEntries.model.Entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
//@EqualsAndHashCode
public class CompositeKey implements Serializable {

//    @Column(name = "restaurant_id")
    Integer restaurantId;

//    @Column(name = "speciality_id")
    Integer specialityId;

}
