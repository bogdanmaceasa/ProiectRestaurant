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
@Table(name = "restaurantspecialities")
@IdClass(CompositeKey.class)
public class RestaurantSpecialitiesEntity {

    @Id
    private Integer restaurantId;

    @Id
    private Integer specialityId;

}
