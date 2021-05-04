package com.siit.finalproject.restaurant.service;


import com.siit.finalproject.address.repository.AddressRepository;
import com.siit.finalproject.address.service.AddressService;
import com.siit.finalproject.exceptions.DuplicateRestaurantEntryException;
import com.siit.finalproject.exceptions.RestaurantNotFoundException;
import com.siit.finalproject.restaurant.mapper.MapperForAddRestaurants;
import com.siit.finalproject.restaurant.mapper.MapperForUpdateRestaurants;
import com.siit.finalproject.restaurant.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurant.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurant.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurant.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurant.repository.RestaurantRepository;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RestaurantsService {


    private final RestaurantRepository restaurantRepository;
    private final MapperForGetRestaurants mapperForGetRestaurants;
    private final MapperForUpdateRestaurants mapperForUpdateRestaurants;
    private final MapperForAddRestaurants mapperForAddRestaurants;
    private final SpecialitiesRepository specialitiesRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;


    public List<RestaurantGetDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(mapperForGetRestaurants::mapEntityToGetDTO)
                .collect(toList());
    }

    public RestaurantGetDTO findByID(Integer id) throws RestaurantNotFoundException {
        return mapperForGetRestaurants.mapEntityToGetDTO(restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID:" + id + " does not exist")));
    }

    public RestaurantGetDTO addRestaurant(RestaurantPostDTO restaurantPostDTO) throws DuplicateRestaurantEntryException {
        // mapperForAddRestaurants IGNORES the ID that is passed by the POST Object
        Optional<RestaurantsEntity> restaurantsEntity = restaurantRepository.findByName(restaurantPostDTO.getName());
        if (restaurantsEntity.isPresent()) {
            if ( restaurantsEntity.get().getAddress().getCity().equals(restaurantPostDTO.getCity())) {

                throw new DuplicateRestaurantEntryException(
                        "Another restaurant with name " +
                                restaurantPostDTO.getName() +
                                " exists in the same city");
            }
        }

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

    public RestaurantGetDTO updateRestaurant(RestaurantPostDTO restaurantPostDTO) throws DuplicateRestaurantEntryException, RestaurantNotFoundException {
        // mapperForPostRestaurants DOES NOT IGNORE the ID that is passed by the PUT Object
        RestaurantsEntity restaurantsEntity = restaurantRepository.findById(restaurantPostDTO.getId())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID:" + restaurantPostDTO.getId() + " does not exist"));
        if ( addressService.checkIfAddressExists(restaurantPostDTO, restaurantsEntity)){
               throw new DuplicateRestaurantEntryException("Another restaurant with address " +
                       restaurantPostDTO.getAddress() +
                       " exists in " + restaurantPostDTO.getCity());
           }


        RestaurantsEntity restaurant = restaurantRepository.save(mapperForUpdateRestaurants.mapDTOToUpdateEntity(restaurantPostDTO));
        return mapperForGetRestaurants.mapEntityToGetDTO(restaurant);
    }

    public void deleteRestaurant(Integer id) throws RestaurantNotFoundException {
        restaurantRepository.findById(id)
                .map(mapperForGetRestaurants::mapEntityToGetDTO)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID:" + id + " does not exist"));
        restaurantRepository.deleteById(id);
    }

}
