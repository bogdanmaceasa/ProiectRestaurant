package com.siit.finalproject.restaurantEntries.service;

import com.siit.finalproject.restaurantEntries.mapper.MapperForGetSpecialities;
import com.siit.finalproject.restaurantEntries.model.DTO.SpecialitiesDTO;
import com.siit.finalproject.restaurantEntries.repository.SpecialitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SpecialitiesService {

    private final SpecialitiesRepository specialitiesRepository;
    private final MapperForGetSpecialities mapperForGetSpecialities;

    public List<SpecialitiesDTO> getAllSpecialities() {
        return specialitiesRepository.findAll()
                .stream()
                .map(s -> mapperForGetSpecialities.mapEntityToGetDTO(s))
                .collect(toList());
    }

}
