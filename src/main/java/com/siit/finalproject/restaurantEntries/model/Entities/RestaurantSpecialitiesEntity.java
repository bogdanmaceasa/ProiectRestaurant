package com.siit.finalproject.restaurantEntries.model.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table( name = "restaurantspecialities")
public class RestaurantSpecialitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private RestaurantsJoinEntity restaurantsEntity;

    @ManyToOne()
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private SpecialitiesEntity specialitiesEntity;

}
