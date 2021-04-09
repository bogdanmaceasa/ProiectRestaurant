package com.siit.finalproject.booking.service;

import com.siit.finalproject.exceptions.*;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import lombok.extern.slf4j.Slf4j;
import com.siit.finalproject.booking.model.DTO.*;
import com.siit.finalproject.booking.mapper.MapperForEditBookings;
import com.siit.finalproject.booking.mapper.MapperForGetBookings;
import com.siit.finalproject.booking.mapper.MapperForGetRestaurantBookingsDTO;
import com.siit.finalproject.booking.mapper.MapperForGetUserBookingsDTO;
import com.siit.finalproject.booking.model.Entities.BookingEntity;
import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.userAccounts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class BookingService {

    private final RestaurantRepository restaurantRepository;
    private final BookingRepository bookingRepository;
    private final UsersRepository usersRepository;
    private final MapperForGetBookings mapperForGetBookings;
    private final MapperForGetRestaurantBookingsDTO mapperForGetRestaurantBookingsDTO;
    private final MapperForGetUserBookingsDTO mapperForGetUserBookingsDTO;
    private final MapperForEditBookings mapperForEditBookings;

    public List<GetBookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(mapperForGetBookings::mapperForGetBookingsEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<GetUserBookingsDTO> getBookingsForUser(Integer id) {
        UsersEntity usersEntity = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user with ID " + id));

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) userDetails).getUsername();
        boolean isAdmin = ((UserDetails) userDetails).getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
//      If the user trying to make this request is NOT an ADMIN,
//      he will only be able to check his and only his bookings
        if (!isAdmin) {
            if (!usersRepository.findByUsername(username).get().getId().equals(id)) {
                throw new MissingRightsException("You can only check your bookings!");
            }
        }
        return bookingRepository.findAllByUserId(usersEntity).stream()
                .sorted(Comparator.comparing((BookingEntity b1) -> b1.getBookingDate().getMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getDayOfMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getHour())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getMinute()))
                .map(mapperForGetUserBookingsDTO::mapEntityToGetUserBookingsDTO)
                .collect(Collectors.toList());
    }

    public List<GetRestaurantBookingsDTO> getBookingsForRestaurant(Integer id) {
        RestaurantsEntity restaurantsEntity = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant with ID " + id));
        return bookingRepository.findAllByRestaurantId(restaurantsEntity).stream()
                .sorted(Comparator.comparing((BookingEntity b1) -> b1.getBookingDate().getMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getDayOfMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getHour())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getMinute()))
                .map(mapperForGetRestaurantBookingsDTO::mapEntityToGetRestaurantBookingDTO)
                .collect(Collectors.toList());
    }

    public GetBookingDTO addBooking(PostBookingDTO postBookingDTO) {
        RestaurantsEntity restaurantsEntity = restaurantRepository.findById(postBookingDTO.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(""));
        UsersEntity usersEntity = usersRepository.findById(postBookingDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) userDetails).getUsername();
        boolean isAdmin = ((UserDetails) userDetails).getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
//      If the user trying to make this request is NOT an ADMIN,
//      he will only be able to add bookings for himself only
        if (!isAdmin) {
            if (!usersRepository.findByUsername(username).get().getId().equals(postBookingDTO.getUserId())) {
                throw new MissingRightsException("You can only add bookings for yourself!");
            }
        }

        int count = bookingRepository.countAllByRestaurantIdAndBookingDateBetween(
                restaurantsEntity,
                postBookingDTO.getBookingDate().minusHours(1),
                postBookingDTO.getBookingDate().plusHours(1));

        if (count >= 5) {
            log.error(count + " booking during the desired interval");
            throw new BookingNotValidException("No tables available on the desired hour");
        }

        BookingEntity bookingEntity = bookingRepository.save(BookingEntity.builder()
                .restaurantId(restaurantsEntity)
                .userId(usersEntity)
                .bookingDate(postBookingDTO.getBookingDate())
                .build());
        return mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingEntity);
    }

    public GetBookingDTO editBooking(EditBookingDTO editBookingDTO) {

        bookingRepository.findById(editBookingDTO.getId())
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID: " + editBookingDTO.getId() + " does not exist"));

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) userDetails).getUsername();
        boolean isAdmin = ((UserDetails) userDetails).getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
//      If the user trying to make this request is NOT an ADMIN,
//      he will only be able to edit his and only his bookings
        if (!isAdmin) {
            if (!usersRepository.findByUsername(username).get().getId()
                    .equals(bookingRepository.findById(editBookingDTO.getId()).get().getUserId().getId())) {
                throw new MissingRightsException("You can only edit your bookings!");
            }
        }
        BookingEntity bookingEntity = mapperForEditBookings.mapEditDTOToEntity(editBookingDTO);
        return mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingRepository.save(bookingEntity));
    }


    public void deleteBooking(Integer id) {

        BookingEntity bookingDTO = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID: " + id + " does not exist"));

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) userDetails).getUsername();
        boolean isAdmin = ((UserDetails) userDetails).getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
//      If the user trying to make this request is NOT an ADMIN,
//      he will only be able to delete his and only his bookings
        if (!isAdmin) {
            if (!usersRepository.findByUsername(username).get().getId().equals(bookingRepository.findById(id).get().getUserId().getId())) {
                throw new MissingRightsException("You can only delete your bookings!");
            }
        }

        bookingRepository.delete(bookingDTO);
    }

}
