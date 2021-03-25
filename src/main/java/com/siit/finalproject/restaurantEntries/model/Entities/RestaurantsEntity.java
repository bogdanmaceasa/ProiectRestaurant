package com.siit.finalproject.restaurantEntries.model.Entities;

import com.siit.finalproject.booking.Booking;
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
@Table(name = "restaurants")
public class RestaurantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @OneToMany(mappedBy = "restaurantsEntity")
    private Set<RestaurantSpecialitiesEntity> specialities;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private DetailsEntity details;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

}
