package com.siit.finalproject.restaurantEntries.model.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.siit.finalproject.address.model.entity.AddressEntity;
import com.siit.finalproject.details.model.Entity.DetailsEntity;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "RestaurantsEntity")
@Table(name = "restaurants")
public class RestaurantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private DetailsEntity details;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "restaurantspecialities",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    @JsonManagedReference
    @Builder.Default
    private Set<SpecialitiesEntity> specialitiesSet = new HashSet<>();

//    @OneToMany(
//            mappedBy = "restaurantId",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
////    @JsonManagedReference
//    @JsonBackReference
//    @Builder.Default
//    private Set<BookingEntity> bookings = new HashSet<>();


    public void addSpeciality(SpecialitiesEntity specialitiesEntity) {
        specialitiesSet.add(specialitiesEntity);
        specialitiesEntity.getRestaurants().add(this);
    }

    public void removeSpeciality(SpecialitiesEntity specialitiesEntity) {
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
