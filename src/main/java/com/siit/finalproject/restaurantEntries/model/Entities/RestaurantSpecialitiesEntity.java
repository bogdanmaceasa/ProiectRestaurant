package com.siit.finalproject.restaurantEntries.model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "restaurantspecialities")
//@IdClass(CompositeKey.class)

public class RestaurantSpecialitiesEntity {

//    @Id
//    private Integer restaurantId;
//
//    @Id
//    private Integer specialityId;
//

    @EmbeddedId
    CompositeKey id;

    @ManyToOne
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
//    @JsonBackReference
    @JsonIgnore
    RestaurantsEntity restaurantsEntity;

    @ManyToOne
    @MapsId("specialityId")
    @JoinColumn(name = "speciality_id")
//    @JsonBackReference
    @JsonIgnore
    SpecialitiesEntity specialitiesEntity;


}



