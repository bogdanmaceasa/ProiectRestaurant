package com.siit.finalproject.specialities.service;

import com.siit.finalproject.specialities.mapper.MapperForGetSpecialities;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import com.siit.finalproject.specialities.model.DTO.SpecialitiesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class SpecialitiesService {

    private final SpecialitiesRepository specialitiesRepository;
    private final MapperForGetSpecialities mapperForGetSpecialities;

    public List<SpecialitiesDTO> getAllSpecialities() {
        return specialitiesRepository.findAll()
                .stream()
                .map(s -> mapperForGetSpecialities.mapSpecialitiesEntityToGetDTO(s))
                .collect(toList());
    }

}
