package com.siit.finalproject._controller;

import com.siit.finalproject.booking.model.DTO.*;
import com.siit.finalproject.booking.service.BookingService;
import com.siit.finalproject.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingRESTController {

    private final BookingService bookingService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetBookingDTO> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetUserBookingsDTO> getBookingsForUser(@RequestParam Integer id) throws UserNotFoundException, MissingRightsException {
        return bookingService.getBookingsForUser(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetRestaurantBookingsDTO> getBookingsForRestaurant(@RequestParam Integer id) throws RestaurantNotFoundException {
        return bookingService.getBookingsForRestaurant(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GetBookingDTO addBooking(@RequestBody PostBookingDTO bookingDTO) throws UserNotFoundException, RestaurantNotFoundException, MissingRightsException, BookingNotValidException {
        return bookingService.addBooking(bookingDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GetBookingDTO editBooking(@RequestBody EditBookingDTO editBookingDTO) throws MissingRightsException, BookingNotFoundException {
        return bookingService.editBooking(editBookingDTO);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBooking(@RequestParam Integer id) throws MissingRightsException, BookingNotFoundException {
        bookingService.deleteBooking(id);
    }

}

