//package com.siit.finalproject.restaurantEntries.model.Entities;
//
//import com.fasterxml.jackson.annotation.*;
//import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Builder
//@Entity
//@Table(name = "restaurantspecialities")
////@IdClass(CompositeKey.class)
//public class RestaurantSpecialitiesEntity {
//
//    @EmbeddedId
//    CompositeKey id;
//
//    @ManyToOne
//    @MapsId("restaurantId")
//    @JoinColumn(name = "restaurant_id")
//    RestaurantsEntity restaurantsEntity;
//
//    @ManyToOne
//    @MapsId("specialityId")
//    @JoinColumn(name = "speciality_id")
//    SpecialitiesEntity specialitiesEntity;
//
//}
