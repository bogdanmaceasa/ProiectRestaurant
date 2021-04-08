package com.siit.finalproject._controller;

import com.siit.finalproject.booking.model.DTO.*;
import com.siit.finalproject.booking.service.BookingService;
import com.siit.finalproject.exceptions.BookingNotValidException;
import com.siit.finalproject.userAccounts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingRESTController {

    private final BookingService bookingService;
    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetBookingDTO> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetUserBookingsDTO> getBookingsForUser(@RequestParam Integer id) {
        return bookingService.getBookingsForUser(id);
    }

    @GetMapping(value = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetRestaurantBookingsDTO> getBookingsForRestaurant(@RequestParam Integer id) {
        return bookingService.getBookingsForRestaurant(id);
    }


//    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<GetBookingDTO> addBooking(@RequestBody PostBookingDTO bookingDTO) {
//        GetBookingDTO booking = bookingService.addBooking(bookingDTO);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
////                .path("/{id}")
//                .buildAndExpand(booking.getId())
//                .toUri();
//        return ResponseEntity.created(uri)
//                .body(booking);
//    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<?>> addBooking(@RequestBody PostBookingDTO bookingDTO) {
        Optional<?> response = bookingService.addBooking(bookingDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(response)
                .toUri();
        if ( response.get().getClass().equals(BookingNotValidException.class) )
            return ResponseEntity.badRequest()
                    .body(response);
        return ResponseEntity.created(uri)
                .body(response);
    }

    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookingDTO> editBooking(@RequestBody EditBookingDTO editBookingDTO) {
        GetBookingDTO getBookingDTO = bookingService.editBooking(editBookingDTO);
        return ResponseEntity.ok()
                .body(getBookingDTO);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<GetBookingDTO> deleteBooking(@RequestParam Integer id) {
        Optional<GetBookingDTO> output = bookingService.deleteBooking(id);
        if (output.isPresent())
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

}

