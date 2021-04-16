package com.siit.finalproject.restaurantTests.service;

import com.siit.finalproject.RequestFactory;
import com.siit.finalproject.address.service.AddressService;
import com.siit.finalproject.exceptions.DuplicateRestaurantEntryException;
import com.siit.finalproject.exceptions.RestaurantNotFoundException;
import com.siit.finalproject.restaurantEntries.mapper.MapperForAddRestaurants;
import com.siit.finalproject.restaurantEntries.mapper.MapperForGetRestaurants;
import com.siit.finalproject.restaurantEntries.mapper.MapperForUpdateRestaurants;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantsServiceTest {

    @InjectMocks
    private RestaurantsService restaurantsService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private  MapperForGetRestaurants mapperForGetRestaurants;

    @Mock
    private  MapperForAddRestaurants mapperForAddRestaurants;

    @Mock
    private AddressService addressService;

    @Mock
    private MapperForUpdateRestaurants mapperForUpdateRestaurants;


    //Tests for restaurantsService.getAllRestaurants()
    @Test
    public void getAllRestaurantsGivenRestaurantsExist(){

        List<RestaurantsEntity> restaurantsEntityList = new ArrayList<>();
        restaurantsEntityList.add(RequestFactory.restaurantsEntity);
        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantsEntityList);
        Mockito.when(mapperForGetRestaurants.mapEntityToGetDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantGetDTO);

        List<RestaurantGetDTO> restaurantGetDTOList = restaurantsService.getAllRestaurants();

        Assertions.assertThat(restaurantGetDTOList).isNotNull();
        Assertions.assertThat(restaurantGetDTOList).isNotEmpty();
        Assertions.assertThat(restaurantGetDTOList.get(0)).isEqualTo(RequestFactory.restaurantGetDTO);
    }

    @Test
    public void getAllRestaurantsGivenNoRestaurants(){

        List<RestaurantsEntity> restaurantsEntityList = new ArrayList<>();
        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurantsEntityList);

        List<RestaurantGetDTO> restaurantGetDTOList = restaurantsService.getAllRestaurants();

        Assertions.assertThat(restaurantGetDTOList).isNotNull();
        Assertions.assertThat(restaurantGetDTOList).isEmpty();

    }


    //Tests for restaurantsService.findByID()
    @Test
    public void findByIdGivenRestaurantExists(){

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(mapperForGetRestaurants.mapEntityToGetDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantGetDTO);

        RestaurantGetDTO restaurantGetDTO = restaurantsService.findByID(1);

        Assertions.assertThat(restaurantGetDTO).isNotNull();
        Assertions.assertThat(restaurantGetDTO).isEqualTo(RequestFactory.restaurantGetDTO);
    }

    @Test
    public void findByIdGivenRestaurantDoesNotExist(){

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        Throwable throwable = Assertions.catchThrowable(() -> restaurantsService.findByID(1));

        Assertions.assertThat(throwable).isInstanceOf(RestaurantNotFoundException.class);
    }


    //Tests for restaurantsService.addRestaurant()
    @Test
    public void addRestaurantGivenRestaurantNotInDatabase(){

        Mockito.when(restaurantRepository.findByName(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Mockito.when(mapperForAddRestaurants.mapAddDTOToEntity(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantsEntity);
        Mockito.when(mapperForGetRestaurants.mapEntityToGetDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantGetDTO);
        Mockito.when(restaurantRepository.save(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantsEntity);
        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));

        RestaurantGetDTO restaurantGetDTO = restaurantsService.addRestaurant(RequestFactory.restaurantPostDTO);

        Assertions.assertThat(restaurantGetDTO).isNotNull();
        Assertions.assertThat(restaurantGetDTO).isEqualTo(RequestFactory.restaurantGetDTO);
    }

    @Test
    public void addRestaurantGivenRestaurantExistsInDatabase(){

        Mockito.when(restaurantRepository.findByName(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));

        Throwable throwable = Assertions.catchThrowable(() -> restaurantsService.addRestaurant(RequestFactory.restaurantPostDTO));

        Assertions.assertThat(throwable).isInstanceOf(DuplicateRestaurantEntryException.class);
    }


    //Tests for restaurantsService.addRestaurantsBulk()
    @Test
    public void addRestaurantsBulk(){
        List<RestaurantPostDTO> restaurantPostDTOList = new ArrayList<>();
        restaurantPostDTOList.add(RequestFactory.restaurantPostDTO);
        Mockito.when(mapperForAddRestaurants.mapAddDTOToEntity(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantsEntity);
        Mockito.when(restaurantRepository.save(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantsEntity);
        Mockito.when(mapperForGetRestaurants.mapEntityToGetDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantGetDTO);

        List<RestaurantGetDTO> restaurantGetDTOList = restaurantsService.addRestaurantsBulk(restaurantPostDTOList);

        Assertions.assertThat(restaurantGetDTOList).isNotEmpty();
        Assertions.assertThat(restaurantGetDTOList.get(0)).isEqualTo(RequestFactory.restaurantGetDTO);
    }


    //Tests for restaurantsService.updateRestaurant()
    @Test
    public void updateRestaurantGivenRestaurantExistsAndNoConflicts(){
        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(addressService.checkIfAddressExists(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(false);
        Mockito.when(restaurantRepository.save(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantsEntity);
        Mockito.when(mapperForGetRestaurants.mapEntityToGetDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantGetDTO);
        Mockito.when(mapperForUpdateRestaurants.mapDTOToUpdateEntity(ArgumentMatchers.any())).thenReturn(RequestFactory.restaurantsEntity);

        RestaurantGetDTO restaurantGetDTO1 = restaurantsService.updateRestaurant(RequestFactory.restaurantPostDTO);
        Assertions.assertThat(restaurantGetDTO1).isEqualTo(RequestFactory.restaurantGetDTO);
    }

    @Test
    public void updateRestaurantGivenRestaurantDoesNotExist(){
        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        Throwable throwable = Assertions.catchThrowable(() ->  restaurantsService.updateRestaurant(RequestFactory.restaurantPostDTO));

        Assertions.assertThat(throwable).isInstanceOf(RestaurantNotFoundException.class);
    }

    @Test
    public void updateRestaurantGivenRestaurantExistsAndAddressExists(){
        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(addressService.checkIfAddressExists(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(true);

        Throwable throwable = Assertions.catchThrowable(() ->  restaurantsService.updateRestaurant(RequestFactory.restaurantPostDTO));

        Assertions.assertThat(throwable).isInstanceOf(DuplicateRestaurantEntryException.class);
    }

}
