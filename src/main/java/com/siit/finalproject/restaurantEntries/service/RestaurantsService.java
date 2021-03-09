package com.siit.finalproject.restaurantEntries.service;


import com.siit.finalproject.restaurantEntries.mapper.MapperForPostRestaurants;
import com.siit.finalproject.restaurantEntries.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
//import com.siit.finalproject.restaurantEntries.repository.RestaurantPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class RestaurantsService {


    private final RestaurantRepository restaurantRepository;
//    private final RestaurantPostRepository restaurantPostRepository;
    private final MapperForGetRestaurants mapperForGetRestaurants;
    private final MapperForPostRestaurants mapperForPostRestaurants;

    public List<RestaurantGetDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(rest -> mapperForGetRestaurants.mapEntityToGetDTO(rest))
                .collect(toList());
    }

    public RestaurantPostDTO addRestaurant(RestaurantPostDTO restaurantPostDTO) {
        restaurantRepository.save(mapperForPostRestaurants.mapPostDTOToEntity(restaurantPostDTO));
        return restaurantPostDTO;
    }

    public Optional<RestaurantGetDTO> deleteRestaurant(Integer id){
        Optional<RestaurantGetDTO> restaurantsEntity = restaurantRepository.findById(id).map(mapperForGetRestaurants::mapEntityToGetDTO);
        restaurantsEntity.ifPresent(s -> restaurantRepository.deleteById(id));
//        restaurantRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());

        return restaurantsEntity;
    }


//
//    public List<RestaurantDTO> searchRestaurantByName(String name) {
//        return restaurantRepository.findAllByNameIsContaining(name)
//                .stream()
//                .map(rest -> restaurantsMapper.mapRestaurantsEntityToDTO(rest))

//                .collect(toList());

//    }


    public RestaurantGetDTO updateRestaurant(RestaurantGetDTO restaurantGetDTO) {
//        restaurantGetRepository.updateRestaurant(
//                restaurantGetDTO.getId(),
//                restaurantGetDTO.getName(),
//                restaurantGetDTO.getAddress(),
//                restaurantGetDTO.getSpecialities(),
//                restaurantGetDTO.getDetails(),
//                restaurantGetDTO.getBooking());

        return restaurantGetDTO;
    }
}
