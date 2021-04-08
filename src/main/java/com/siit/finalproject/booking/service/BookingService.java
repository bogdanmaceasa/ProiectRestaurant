package com.siit.finalproject.booking.service;

import com.siit.finalproject.exceptions.BookingNotFoundException;
import com.siit.finalproject.exceptions.BookingNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        return bookingRepository.findAll().stream().map(mapperForGetBookings::mapperForGetBookingsEntityToDTO).collect(Collectors.toList());
    }

    public List<GetUserBookingsDTO> getBookingsForUser(Integer id) {
        return bookingRepository.findAllByUserId(usersRepository.findById(id).get()).stream()
                .sorted(Comparator.comparing((BookingEntity b1) -> b1.getBookingDate().getMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getDayOfMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getHour())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getMinute()))
                .map(mapperForGetUserBookingsDTO::mapEntityToGetUserBookingsDTO)
                .collect(Collectors.toList());
    }

    public List<GetRestaurantBookingsDTO> getBookingsForRestaurant(Integer id) {
        return bookingRepository.findAllByRestaurantId(restaurantRepository.findById(id).get()).stream()
                .sorted(Comparator.comparing((BookingEntity b1) -> b1.getBookingDate().getMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getDayOfMonth())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getHour())
                        .thenComparing((BookingEntity b1) -> b1.getBookingDate().getMinute()))
                .map(mapperForGetRestaurantBookingsDTO::mapEntityToGetRestaurantBookingDTO)
                .collect(Collectors.toList());
    }

    public GetBookingDTO addBooking(PostBookingDTO postBookingDTO) throws BookingNotValidException {
        int count = bookingRepository.countAllByRestaurantIdAndBookingDateBetween(restaurantRepository.findById(postBookingDTO.getRestaurantId()).get(),postBookingDTO.getBookingDate().minusHours(2),postBookingDTO.getBookingDate().plusHours(1));
        System.out.println(count + " booking during the desired interval");
        if (count > 5) {
            log.error(count + " booking during the desired interval");
            throw new BookingNotValidException("No tables available on the desired hour");
        }
        BookingEntity bookingEntity = bookingRepository.save(BookingEntity.builder()
                .restaurantId(restaurantRepository.findById(postBookingDTO.getRestaurantId()).get())
                .userId(usersRepository.findById(postBookingDTO.getUserId()).get())
                .bookingDate(postBookingDTO.getBookingDate())
                .build());
        return mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingEntity);
    }

    public GetBookingDTO editBooking(EditBookingDTO editBookingDTO) {
        bookingRepository.findById(editBookingDTO.getId()).orElseThrow(() -> new BookingNotFoundException("Booking with ID: " + editBookingDTO.getId() + " does not exist"));
        BookingEntity bookingEntity = mapperForEditBookings.mapEditDTOToEntity(editBookingDTO);
        return mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingRepository.save(bookingEntity));
    }

    public GetBookingDTO deleteBooking(Integer id) {
        GetBookingDTO bookingDTO = bookingRepository.findById(id)
                .map(s -> mapperForGetBookings.mapperForGetBookingsEntityToDTO(s))
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID: " + id + " does not exist"));
        bookingRepository.deleteById(bookingDTO.getId());
        return bookingDTO;
    }

}
