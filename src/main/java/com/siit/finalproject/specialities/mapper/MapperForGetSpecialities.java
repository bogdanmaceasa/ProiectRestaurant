package com.siit.finalproject.specialities.mapper;

import com.siit.finalproject.specialities.model.DTO.SpecialitiesDTO;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
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
