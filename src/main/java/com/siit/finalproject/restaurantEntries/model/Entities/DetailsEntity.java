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
@Table( name = "details")
public class DetailsEntity {

    @Id
    private int id;

    private String details;
}
