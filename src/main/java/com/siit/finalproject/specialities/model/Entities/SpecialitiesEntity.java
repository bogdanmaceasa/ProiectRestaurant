package com.siit.finalproject.specialities.model.Entities;


import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantSpecialitiesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table( name = "specialities")
public class SpecialitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    @OneToMany(mappedBy = "specialityId")
    private Set<RestaurantSpecialitiesEntity> restaurants;

}
