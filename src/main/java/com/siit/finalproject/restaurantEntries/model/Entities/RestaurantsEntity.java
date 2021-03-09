package com.siit.finalproject.restaurantEntries.model.Entities;

import com.siit.finalproject.booking.Booking;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table( name = "restaurants")
public class RestaurantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")    // insertable=false, updatable=false
    private AddressEntity address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialities_id", referencedColumnName = "id")
    private SpecialitiesEntity specialities;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private DetailsEntity details;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

}
