package com.siit.finalproject.specialities.model.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "specialities")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "restaurants")
public class SpecialitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    @OneToMany(mappedBy = "specialitiesEntity", fetch = FetchType.LAZY)
//    @JsonManagedReference
    private Set<RestaurantSpecialitiesEntity> restaurants;

}
