package com.siit.finalproject.restaurantEntries.model.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table( name = "type")
public class SpecialitiesEntity {

    @Id
    private int id;

    private String type1;

    private String type2;

    private String type3;

}
