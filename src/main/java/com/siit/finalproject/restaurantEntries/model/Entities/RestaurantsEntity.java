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

    @ToString.Exclude
    @Column(name = "address_id")
    private int addressId;

    @ToString.Exclude
    @Column(name = "type_id")
    private int typeId;

    @ToString.Exclude
    @Column(name = "details_id")
    private int detailsId;

    @ToString.Exclude
    @Column(name = "booking_id")
    private int bookingId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id", insertable=false, updatable=false)
    private SpecialitiesEntity specialities;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id", referencedColumnName = "id", insertable=false, updatable=false)
    private DetailsEntity details;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Booking booking;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", insertable=false, updatable=false)
    private AddressEntity address;
}
