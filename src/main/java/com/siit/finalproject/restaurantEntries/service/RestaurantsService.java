package com.siit.finalproject.restaurantEntries.service;


import com.siit.finalproject.exceptions.RestaurantNotFoundException;
import com.siit.finalproject.restaurantEntries.mapper.MapperForAddRestaurants;
import com.siit.finalproject.restaurantEntries.mapper.MapperForUpdateRestaurants;
import com.siit.finalproject.restaurantEntries.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantsService {


    private final RestaurantRepository restaurantRepository;
    private final MapperForGetRestaurants mapperForGetRestaurants;
    private final MapperForUpdateRestaurants mapperForUpdateRestaurants;
    private final MapperForAddRestaurants mapperForAddRestaurants;
    private final SpecialitiesRepository specialitiesRepository;


    public List<RestaurantGetDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(mapperForGetRestaurants::mapEntityToGetDTO)
                .collect(toList());
    }

    public RestaurantGetDTO addRestaurant(RestaurantPostDTO restaurantPostDTO) {
        // mapperForAddRestaurants IGNORES the ID that is passed by the POST Object
        RestaurantsEntity restaurant = restaurantRepository.save(mapperForAddRestaurants.mapAddDTOToEntity(restaurantPostDTO));
        return mapperForGetRestaurants.mapEntityToGetDTO(restaurantRepository.findById(restaurant.getId()).get());
    }

    public List<RestaurantGetDTO> addRestaurantsBulk(List<RestaurantPostDTO> restaurantPostDTOList) {
        // mapperForAddRestaurants IGNORES the ID that is passed by the POST Object
        return restaurantPostDTOList.stream()
                .map(restaurantPostDTO -> restaurantRepository.save(mapperForAddRestaurants.mapAddDTOToEntity(restaurantPostDTO)))
                .map(mapperForGetRestaurants::mapEntityToGetDTO)
                .collect(toList());
    }

    public RestaurantGetDTO updateRestaurant(RestaurantPostDTO restaurantPostDTO) {
        // mapperForPostRestaurants DOES NOT IGNORE the ID that is passed by the PUT Object
        restaurantRepository.findById(restaurantPostDTO.getId())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID:" + restaurantPostDTO.getId() + " does not exist"));
        RestaurantsEntity restaurant = restaurantRepository.save(mapperForUpdateRestaurants.mapDTOToUpdateEntity(restaurantPostDTO));
        return mapperForGetRestaurants.mapEntityToGetDTO(restaurant);
    }

    public void deleteRestaurant(Integer id) {
        restaurantRepository.findById(id)
                .map(mapperForGetRestaurants::mapEntityToGetDTO)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID:" + id + " does not exist"));
        restaurantRepository.deleteById(id);
    }

    public RestaurantGetDTO findByID(Integer id) {
        return mapperForGetRestaurants.mapEntityToGetDTO(restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID:" + id + " does not exist")));
    }


//    public List<RestaurantDTO> searchRestaurantByName(String name) {
//        return restaurantRepository.findAllByNameIsContaining(name)
//                .stream()
//                .map(rest -> restaurantsMapper.mapRestaurantsEntityToDTO(rest))
//                .collect(toList());
//    }


}
