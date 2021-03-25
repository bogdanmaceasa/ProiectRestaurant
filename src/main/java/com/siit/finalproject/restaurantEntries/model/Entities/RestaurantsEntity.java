package com.siit.finalproject.restaurantEntries.model.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.siit.finalproject.booking.Booking;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "restaurants")
@Table(name = "restaurants")
public class RestaurantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private DetailsEntity details;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "restaurantspecialities",
            joinColumns = @JoinColumn(name="restaurant_id"),
            inverseJoinColumns= @JoinColumn(name="speciality_id"))
    @JsonManagedReference
    private Set<SpecialitiesEntity> specialitiesSet = new HashSet<>();

    public void addSpeciality(SpecialitiesEntity specialitiesEntity){
        specialitiesSet.add(specialitiesEntity);
        specialitiesEntity.getRestaurants().add(this);
    }

    public void removeSpeciality(SpecialitiesEntity specialitiesEntity){
        specialitiesSet.remove(specialitiesEntity);
        specialitiesEntity.getRestaurants().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantsEntity)) return false;
        return id != null && id.equals(((RestaurantsEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
