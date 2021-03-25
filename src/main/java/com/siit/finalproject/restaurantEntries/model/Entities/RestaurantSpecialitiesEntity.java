package com.siit.finalproject.restaurantEntries.model.Entities;

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
    RestaurantsEntity restaurantsEntity;

    @ManyToOne
    @MapsId("specialityId")
    @JoinColumn(name = "speciality_id")
    SpecialitiesEntity specialitiesEntity;


}



