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
import com.siit.finalproject.exceptions.*;
import com.siit.finalproject.restaurant.repository.RestaurantRepository;
import com.siit.finalproject.security.service.GetDataFromSecurityContext;
import com.siit.finalproject.userAccounts.repository.UsersRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import org.assertj.core.api.Assertions;
import org.mockito.junit.MockitoJUnitRunner;



import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {


    @Rule
    public final ExpectedException exception = ExpectedException.none();

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

    @Mock
    private GetDataFromSecurityContext getDataFromSecurityContext;


    //Tests for bookingService.getAllBookings()
    @Test
    public void getAllBookingsGivenNoBookingsReturnEmptyList() {

        List<BookingEntity> bookingEntityList = new ArrayList<>();
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingEntityList);

        List<GetBookingDTO> bookingDTOList = bookingService.getAllBookings();

        Assertions.assertThat(bookingDTOList).isNotNull();
        Assertions.assertThat(bookingDTOList.isEmpty()).isTrue();
    }

    @Test
    public void getAllBookingsGivenBookingsExist() {

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


    //Tests for bookingService.getBookingsForUser()
    @Test
    public void getBookingsForUserGivenUserDoesNotExist() {

        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        Throwable thrown = Assertions.catchThrowable(() -> bookingService.getBookingsForUser(1));
        Assertions.assertThat(thrown).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void getBookingsForUserGivenUserIsCheckingHisBookingsAndBookingsExist() throws UserNotFoundException, MissingRightsException {

        Mockito.when(bookingRepository.findAllByUserId(ArgumentMatchers.any())).thenReturn(Set.of(RequestFactory.booking));
        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(mapperForGetUserBookingsDTO.mapEntityToGetUserBookingsDTO(RequestFactory.booking)).thenReturn(RequestFactory.getUserBookingsDTO);
        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(1)).thenReturn(true);

        List<GetUserBookingsDTO> bookingEntitySet = bookingService.getBookingsForUser(1);

        Assertions.assertThat(bookingEntitySet).isNotNull();
        Assertions.assertThat(bookingEntitySet.size()).isEqualTo(1);
        Assertions.assertThat(bookingEntitySet.get(0).getRestaurant()).isEqualTo(RequestFactory.restaurantGetDTO);

    }

    @Test
    public void getBookingsForUserGivenUserIsCheckingHisBookingsAndBookingsDoNotExist() throws UserNotFoundException, MissingRightsException {

        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(bookingRepository.findAllByUserId(ArgumentMatchers.any())).thenReturn(Set.of());

        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(1)).thenReturn(true);

        List<GetUserBookingsDTO> bookingEntitySet = bookingService.getBookingsForUser(1);

        Assertions.assertThat(bookingEntitySet).isEmpty();
        Assertions.assertThat(bookingEntitySet).isNotNull();

    }

    @Test
    public void getBookingsForUserGivenUserIsCheckingOtherBookingsOrIsNotAdmin() throws UserNotFoundException, MissingRightsException {

        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(1)).thenReturn(false);
        exception.expect(MissingRightsException.class);
        bookingService.getBookingsForUser(1);

    }


    //Tests for bookingService.getBookingsForRestaurant()
    @Test
    public void getBookingsForRestaurantGivenRestaurantAndBookingsExist() throws RestaurantNotFoundException {

        Set<BookingEntity> bookingEntityList = new HashSet<>();
        bookingEntityList.add(RequestFactory.booking);
        Mockito.when(restaurantRepository.findById(1)).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(bookingRepository.findAllByRestaurantId(ArgumentMatchers.any())).thenReturn(bookingEntityList);
        Mockito.when(mapperForGetRestaurantBookingsDTO.mapEntityToGetRestaurantBookingDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.getRestaurantBookingsDTO);

        List<GetRestaurantBookingsDTO> bookingDTOList = bookingService.getBookingsForRestaurant(1);

        Assertions.assertThat(bookingDTOList).isNotNull();
        Assertions.assertThat(bookingDTOList.size()).isEqualTo(1);
        Assertions.assertThat(bookingDTOList.get(0).getUser()).isEqualTo(RequestFactory.userDTO);

    }

    @Test
    public void getBookingsForRestaurantGivenRestaurantDoesNotExist() {

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Throwable thrown = Assertions.catchThrowable(() -> bookingService.getBookingsForRestaurant(1));

        Assertions.assertThat(thrown).isInstanceOf(RestaurantNotFoundException.class);

    }

    @Test
    public void getBookingsForRestaurantGivenRestaurantExistsAndHasNoBookings() throws RestaurantNotFoundException {

        Set<BookingEntity> bookingEntityList = new HashSet<>();
        Mockito.when(restaurantRepository.findById(1)).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(bookingRepository.findAllByRestaurantId(ArgumentMatchers.any())).thenReturn(bookingEntityList);

        List<GetRestaurantBookingsDTO> bookingDTOList = bookingService.getBookingsForRestaurant(1);

        Assertions.assertThat(bookingDTOList).isNotNull();
        Assertions.assertThat(bookingDTOList.isEmpty()).isTrue();

    }


    //Tests for bookingService.addBooking()
    @Test
    public void addBookingGivenUserAddsForHimselfAndUserExistsAndRestaurantExistsAndLessThanFiveBookingsForRestaurant() throws UserNotFoundException, RestaurantNotFoundException, MissingRightsException, BookingNotValidException {

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(1)).thenReturn(true);
        Mockito.when(bookingRepository.countAllByRestaurantIdAndBookingDateBetween(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(3);
        Mockito.when(mapperForGetBookings.mapperForGetBookingsEntityToDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.getBookingDTO);

        GetBookingDTO bookingEntity = bookingService.addBooking(RequestFactory.postBookingDTO);

        Assertions.assertThat(bookingEntity).isEqualTo(RequestFactory.getBookingDTO);

    }

    @Test
    public void addBookingGivenUserAddsForHimselfAndRestaurantDoesNotExist() {

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        Throwable thrown = Assertions.catchThrowable(() -> bookingService.addBooking(RequestFactory.postBookingDTO));

        Assertions.assertThat(thrown).isInstanceOf(RestaurantNotFoundException.class);

    }

    @Test
    public void addBookingGivenUserDoesNotExist() {

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        Throwable thrown = Assertions.catchThrowable(() -> bookingService.addBooking(RequestFactory.postBookingDTO));

        Assertions.assertThat(thrown).isInstanceOf(UserNotFoundException.class);

    }

    @Test
    public void addBookingGivenUserIsNotAdminOrIsTryingToAddForAnotherUser() {

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(ArgumentMatchers.any())).thenReturn(false);

        Throwable thrown = Assertions.catchThrowable(() -> bookingService.addBooking(RequestFactory.postBookingDTO));

        Assertions.assertThat(thrown).isInstanceOf(MissingRightsException.class);

    }

    @Test
    public void addBookingGivenUserAddsForHimselfAndUserExistsAndRestaurantExistsAndMoreThanFiveBookingsForRestaurant() {

        Mockito.when(restaurantRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.restaurantsEntity));
        Mockito.when(usersRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.usersEntity));
        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(1)).thenReturn(true);
        Mockito.when(bookingRepository.countAllByRestaurantIdAndBookingDateBetween(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(6);
//        Mockito.when(mapperForGetBookings.mapperForGetBookingsEntityToDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.getBookingDTO);

        Throwable thrown = Assertions.catchThrowable(() -> bookingService.addBooking(RequestFactory.postBookingDTO));

        Assertions.assertThat(thrown).isInstanceOf(BookingNotValidException.class);

    }


    //Tests for bookingService.editBooking()
    @Test
    public void editBookingGivenBookingExistsAndUserHasPermission() throws MissingRightsException, BookingNotFoundException {
        Mockito.when(bookingRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.booking));
        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(1)).thenReturn(true);
        Mockito.when(mapperForEditBookings.mapEditDTOToEntity(ArgumentMatchers.any())).thenReturn(RequestFactory.booking);
        Mockito.when(mapperForGetBookings.mapperForGetBookingsEntityToDTO(ArgumentMatchers.any())).thenReturn(RequestFactory.getBookingDTO);

        GetBookingDTO getBookingDTO = bookingService.editBooking(RequestFactory.editBookingDTO);

        Assertions.assertThat(getBookingDTO).isEqualTo(RequestFactory.getBookingDTO);

    }

    @Test
    public void editBookingGivenBookingDoesNotExist() {
        Mockito.when(bookingRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        Throwable thrown = Assertions.catchThrowable(() -> bookingService.editBooking(RequestFactory.editBookingDTO));

        Assertions.assertThat(thrown).isInstanceOf(BookingNotFoundException.class);

    }

    @Test
    public void editBookingGivenBookingExistsAndUserDoesNotHaveRights() {
        Mockito.when(bookingRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(RequestFactory.booking));
        Mockito.when(getDataFromSecurityContext.checkIfRequestPermittedForLoggedUser(1)).thenReturn(false);
        Throwable thrown = Assertions.catchThrowable(() -> bookingService.editBooking(RequestFactory.editBookingDTO));

        Assertions.assertThat(thrown).isInstanceOf(MissingRightsException.class);

    }

}
