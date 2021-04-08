package com.siit.finalproject.booking.service;

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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
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


//    public GetBookingDTO addBooking(PostBookingDTO postBookingDTO) {
//        int count = bookingRepository.countAllByRestaurantIdAndBookingDateBetween(restaurantRepository.findById(postBookingDTO.getRestaurantId()).get(),postBookingDTO.getBookingDate().minusHours(1),postBookingDTO.getBookingDate().plusHours(1));
//        System.out.println("COUNT IS " + count);
//        BookingEntity bookingEntity = bookingRepository.save(BookingEntity.builder()
//                .restaurantId(restaurantRepository.findById(postBookingDTO.getRestaurantId()).get())
//                .userId(usersRepository.findById(postBookingDTO.getUserId()).get())
//                .build());
//        return mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingEntity);
//    }
//
//    public GetBookingDTO editBooking(EditBookingDTO editBookingDTO) {
//        return mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingRepository.save(mapperForEditBookings.mapEditDTOToEntity(editBookingDTO)));
//    }

    public Optional<?> addBooking(PostBookingDTO postBookingDTO) {
        int count = bookingRepository.countAllByRestaurantIdAndBookingDateBetween(restaurantRepository.findById(postBookingDTO.getRestaurantId()).get(),postBookingDTO.getBookingDate().minusHours(1),postBookingDTO.getBookingDate().plusHours(1));
        System.out.println("COUNT IS " + count);
        if (count > 5) {
            return Optional.of(new IllegalArgumentException("No tables available on the desired hour"));
        }
        BookingEntity bookingEntity = bookingRepository.save(BookingEntity.builder()
                .restaurantId(restaurantRepository.findById(postBookingDTO.getRestaurantId()).get())
                .userId(usersRepository.findById(postBookingDTO.getUserId()).get())
                .build());
        return Optional.of(mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingEntity));
    }

    public GetBookingDTO editBooking(EditBookingDTO editBookingDTO) {
        return mapperForGetBookings.mapperForGetBookingsEntityToDTO(bookingRepository.save(mapperForEditBookings.mapEditDTOToEntity(editBookingDTO)));
    }

    public Optional<GetBookingDTO> deleteBooking(Integer id) {
        Optional<GetBookingDTO> bookingDTO = bookingRepository.findById(id)
                .map(s -> mapperForGetBookings.mapperForGetBookingsEntityToDTO(s));
        bookingDTO.ifPresent(s -> bookingRepository.deleteById(id));
        return bookingDTO;
    }

}
