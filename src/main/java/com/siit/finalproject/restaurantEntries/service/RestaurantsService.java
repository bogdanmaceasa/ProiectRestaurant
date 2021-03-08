package com.siit.finalproject.restaurantEntries.service;


import com.siit.finalproject.restaurantEntries.mapper.RestaurantsDTOToEntityMapper;
import com.siit.finalproject.restaurantEntries.mapper.RestaurantsMapper;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONUtil;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class RestaurantsService {


    private final RestaurantRepository restaurantRepository;
    private final RestaurantsMapper restaurantsMapper;
    private final RestaurantsDTOToEntityMapper restaurantsDTOToEntityMapper;

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(rest -> restaurantsMapper.mapRestaurantsEntityToDTO(rest))
                .collect(toList());
    }


    public List<RestaurantDTO> searchRestaurantByName(String name) {
        return restaurantRepository.findAllByNameIsContaining(name)
                .stream()
                .map(rest -> restaurantsMapper.mapRestaurantsEntityToDTO(rest))
                .collect(toList());
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        restaurantRepository.save(restaurantsDTOToEntityMapper.putDTOinEntity(restaurantDTO));
        return restaurantDTO;
    }


    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {
        restaurantRepository.updateRestaurant(
                restaurantDTO.getId(),
                restaurantDTO.getName(),
                restaurantDTO.getAddress(),
                restaurantDTO.getSpecialities(),
                restaurantDTO.getDetails(),
                restaurantDTO.getBooking());

        return restaurantDTO;
    }

    public Optional<RestaurantDTO> deleteRestaurant(Integer id){
        Optional<RestaurantDTO> restaurantsEntity = restaurantRepository.findById(id).map(restaurantsMapper::mapRestaurantsEntityToDTO);
        restaurantsEntity.ifPresent(s -> restaurantRepository.deleteById(id));

        return restaurantsEntity;
    }
}
