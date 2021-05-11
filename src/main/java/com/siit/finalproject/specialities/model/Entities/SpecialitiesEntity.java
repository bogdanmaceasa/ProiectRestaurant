package com.siit.finalproject.specialities.model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.siit.finalproject.restaurant.model.entities.RestaurantsEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "specialities")
@Table(name = "specialities")
public class SpecialitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    private String type;

    @ManyToMany(mappedBy = "specialitiesSet")
    @JsonBackReference
    private Set<RestaurantsEntity> restaurants = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialitiesEntity specialitiesEntity = (SpecialitiesEntity) o;
        return Objects.equals(type, specialitiesEntity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }


}
