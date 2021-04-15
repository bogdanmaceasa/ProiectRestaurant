package com.siit.finalproject.bookingTests.service;

import com.siit.finalproject.RequestFactory;
import com.siit.finalproject.booking.mapper.MapperForEditBookings;
import com.siit.finalproject.booking.mapper.MapperForGetBookings;
import com.siit.finalproject.booking.mapper.MapperForGetRestaurantBookingsDTO;
import com.siit.finalproject.booking.mapper.MapperForGetUserBookingsDTO;
import com.siit.finalproject.booking.model.DTO.*;
import com.siit.finalproject.booking.model.Entities.BookingEntity;
import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.booking.service.BookingService;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.userAccounts.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import org.assertj.core.api.Assertions;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {


    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private MapperForGetBookings mapperForGetBookings;

    @Mock
    private MapperForGetRestaurantBookingsDTO mapperForGetRestaurantBookingsDTO;

    @Mock
    private MapperForGetUserBookingsDTO mapperForGetUserBookingsDTO;

    @Mock
    private MapperForEditBookings mapperForEditBookings;


    @Test
    public void getAllBookingsGivenNoBookingsReturnEmptyList() {

        List<BookingEntity> bookingEntityList = new ArrayList<>();
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingEntityList);

        List<GetBookingDTO> bookingDTOList = bookingService.getAllBookings();

        Assertions.assertThat(bookingDTOList).isNotNull();
        Assertions.assertThat(bookingDTOList.isEmpty()).isTrue();
    }

    @Test
    public void getAllBookingsGivenBookings() {

        List<BookingEntity> bookingEntityList = new ArrayList<>();
        bookingEntityList.add(RequestFactory.booking);
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingEntityList);
        Mockito.when(mapperForGetBookings.mapperForGetBookingsEntityToDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.getBookingDTO);

        List<GetBookingDTO> bookingDTOList = bookingService.getAllBookings();

        Assertions.assertThat(bookingDTOList).isNotNull();
        Assertions.assertThat(bookingDTOList.size()).isEqualTo(1);
        Assertions.assertThat(bookingDTOList.get(0)).isNotNull();
        Assertions.assertThat(bookingDTOList.get(0).getRestaurant()).isEqualTo(RequestFactory.restaurantGetDTO);
    }

    @Test
    @WithMockUser(username = "bogdanmaceasa", authorities = { "ADMIN", "USER" })
    public void getBookingsForUser() {

        Mockito.when(bookingRepository.findAllByUserId(RequestFactory.usersEntity)).thenReturn(Set.of(RequestFactory.booking));
        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(usersRepository.findByUsername(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(mapperForGetUserBookingsDTO.mapEntityToGetUserBookingsDTO(RequestFactory.booking)).thenReturn(RequestFactory.getUserBookingsDTO);

        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(RequestFactory.userDetails);
        Mockito.when(((UserDetails)RequestFactory.userDetails).getUsername()).thenReturn(RequestFactory.usersEntity.getUsername());
        Mockito.when(((UserDetails)RequestFactory.userDetails).getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))).thenReturn(true);

        List<GetUserBookingsDTO> bookingEntitySet = bookingService.getBookingsForUser(1);

        Assertions.assertThat(bookingEntitySet).isNotNull();
        Assertions.assertThat(bookingEntitySet.size()).isEqualTo(1);
        Assertions.assertThat(bookingEntitySet.get(0).getRestaurant()).isEqualTo(RequestFactory.restaurantGetDTO);

    }

    public void getBookingsForRestaurant(Integer id) {

    }

    public void addBooking(PostBookingDTO postBookingDTO) {

    }

    public void editBooking(EditBookingDTO editBookingDTO) {

    }


    public void deleteBooking(Integer id) {

    }



}
