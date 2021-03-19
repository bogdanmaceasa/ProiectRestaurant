package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.SpecialitiesDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.SpecialitiesEntity;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Builder
@RequiredArgsConstructor
@Component
public class MapperForGetSpecialities {

    public SpecialitiesDTO mapEntityToGetDTO(SpecialitiesEntity specialitiesEntity) {
        return SpecialitiesDTO.builder()
                .id(specialitiesEntity.getId())
                .type(specialitiesEntity.getType())
                .build();
    }

}
