package com.siit.finalproject.restaurantEntries.service;


import com.siit.finalproject.restaurantEntries.mapper.MapperForAddRestaurants;
import com.siit.finalproject.restaurantEntries.mapper.MapperForPostRestaurants;
import com.siit.finalproject.restaurantEntries.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantSpecialitiesEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.restaurantEntries.repository.RestaurantSpecialitiesRepository;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class RestaurantsService {


    private final RestaurantRepository restaurantRepository;
    private final MapperForGetRestaurants mapperForGetRestaurants;
    private final MapperForPostRestaurants mapperForPostRestaurants;
    private final MapperForAddRestaurants mapperForAddRestaurants;
    private final SpecialitiesRepository specialitiesRepository;
    private final RestaurantSpecialitiesRepository restaurantSpecialitiesRepository;


    public List<RestaurantGetDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(mapperForGetRestaurants::mapEntityToGetDTO)
                .collect(toList());

    }

    public RestaurantPostDTO updateRestaurant(RestaurantPostDTO restaurantPostDTO) {
        // mapperForAddRestaurants DOES NOT IGNORE the ID that is passed by the POST Method
        RestaurantsEntity restaurant = restaurantRepository.save(mapperForPostRestaurants.mapPostDTOToEntity(restaurantPostDTO));
        Set<SpecialitiesEntity> specialitiesEntitySet = restaurantPostDTO.getSpecialities()
                                                                            .stream()
                                                                            .map(s -> specialitiesRepository.findById(s).get())
                                                                            .collect(Collectors.toSet());
        specialitiesEntitySet.stream()
                                .forEach(s-> restaurantSpecialitiesRepository.save(RestaurantSpecialitiesEntity.builder()
                                                                                .restaurantId(restaurant.getId())
                                                                                .specialityId(s.getId())
                                                                                .build()));


        return restaurantPostDTO;
    }


    public RestaurantGetDTO addRestaurant(RestaurantPostDTO restaurantPostDTO) {
        // mapperForAddRestaurants IGNORES the ID that is passed by the POST Method
        RestaurantsEntity restaurant = restaurantRepository.save(mapperForAddRestaurants.mapAddDTOToEntity(restaurantPostDTO));
        Set<SpecialitiesEntity> specialitiesEntitySet = restaurantPostDTO.getSpecialities()
                .stream()
                .map(s -> specialitiesRepository.findById(s).get())
                .collect(Collectors.toSet());
        specialitiesEntitySet.stream()
                .forEach(s-> restaurantSpecialitiesRepository.save(RestaurantSpecialitiesEntity.builder()
                        .restaurantId(restaurant.getId())
                        .specialityId(s.getId())
                        .build()));
        return mapperForGetRestaurants.mapEntityToGetDTO(restaurantRepository.findByName(restaurantPostDTO.getName()));
    }

    public Optional<RestaurantGetDTO> deleteRestaurant(Integer id){
        Optional<RestaurantGetDTO> restaurantsEntity = restaurantRepository.findById(id)
                                                                            .map(mapperForGetRestaurants::mapEntityToGetDTO);
        restaurantsEntity.ifPresent(s -> restaurantRepository.deleteById(id));
//        restaurantRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());

        return restaurantsEntity;
    }

//    public List<RestaurantDTO> searchRestaurantByName(String name) {
//        return restaurantRepository.findAllByNameIsContaining(name)
//                .stream()
//                .map(rest -> restaurantsMapper.mapRestaurantsEntityToDTO(rest))

//                .collect(toList());

//    }


}
