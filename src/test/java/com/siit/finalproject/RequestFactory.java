package com.siit.finalproject;

import com.siit.finalproject.address.model.entity.AddressEntity;
import com.siit.finalproject.booking.model.DTO.*;
import com.siit.finalproject.booking.model.Entities.BookingEntity;
import com.siit.finalproject.details.model.Entity.DetailsEntity;
import com.siit.finalproject.restaurant.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurant.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurant.model.entities.RestaurantsEntity;
import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import com.siit.finalproject.userAccounts.model.Entities.RoleEnum;
import com.siit.finalproject.userAccounts.model.Entities.RolesEntity;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;


import java.time.LocalDateTime;
import java.util.Set;

public class RequestFactory {

    public static String location = "/src/main/resources/";

    public static AddressEntity addressEntity = AddressEntity.builder()
                .street("Calea Bucuresti")
                .city("Brasov")
                .build();

    public static DetailsEntity detailsEntity = DetailsEntity.builder()
            .details(location)
            .build();

    public static SpecialitiesEntity specialitiesEntity = SpecialitiesEntity.builder()
            .type("italian")
            .build();

    public static RolesEntity rolesEntity = RolesEntity.builder()
            .role(RoleEnum.ADMIN)
            .build();


    //USER ENTITIES

    public static UsersEntity usersEntity = UsersEntity.builder()
            .name("Bogdan")
            .email("bogdan.maceasa@gmail.com")
            .username("bogdanmaceasa")
            .password("!!!password123!!!")
            .roles(Set.of(rolesEntity))
            .id(1)
            .build();

    public static UserDTO userDTO = UserDTO.builder()
            .userType(1)
            .name("Bogdan")
            .email("bogdan.maceasa@gmail.com")
            .id(1)
            .build();

    //RESTAURANT ENTITIES

    public static RestaurantsEntity restaurantsEntity = RestaurantsEntity.builder()
            .details(detailsEntity)
            .specialitiesSet(Set.of(specialitiesEntity))
            .name("restaurantsEntity")
            .address(addressEntity)
            .build();

    public static RestaurantGetDTO restaurantGetDTO = RestaurantGetDTO.builder()
            .city("Brasov")
            .specialities(Set.of("italian"))
            .address("Calea Bucuresti")
            .details("Calea Bucuresti, Brasov,Queen's Pub")
            .name("Queen's Pub")
            .id(1)
            .build();

    public static RestaurantPostDTO restaurantPostDTO = RestaurantPostDTO.builder()
            .city("Brasov")
            .specialities(Set.of("italian"))
            .address("Calea Bucuresti")
            .detailsInput("Calea Bucuresti, Brasov,Queen's Pub")
            .name("Queen's Pub")
            .id(1)
            .build();


    //BOOKING ENTITIES

    public static PostBookingDTO postBookingDTO =  PostBookingDTO.builder()
            .bookingDate(LocalDateTime.now())
            .restaurantId(1)
            .userId(1)
            .build();

    public static EditBookingDTO editBookingDTO = EditBookingDTO.builder()
            .bookingDate(LocalDateTime.now())
            .id(1)
            .restaurantId(1)
            .userId(1)
            .build();

    public static BookingEntity booking = BookingEntity.builder()
            .bookingDate(LocalDateTime.now())
            .restaurantId(restaurantsEntity)
            .userId(usersEntity)
            .build();

    public static GetBookingDTO getBookingDTO = GetBookingDTO.builder()
            .bookingDate(LocalDateTime.now())
            .restaurant(restaurantGetDTO)
            .user(userDTO)
            .build();

    public static GetUserBookingsDTO getUserBookingsDTO = GetUserBookingsDTO.builder()
            .bookingDate(LocalDateTime.now())
            .restaurant(restaurantGetDTO)
            .status("new")
            .id(1)
            .build();

    public static GetRestaurantBookingsDTO getRestaurantBookingsDTO = GetRestaurantBookingsDTO.builder()
            .bookingDate(LocalDateTime.now())
            .user(userDTO)
            .build();

}
