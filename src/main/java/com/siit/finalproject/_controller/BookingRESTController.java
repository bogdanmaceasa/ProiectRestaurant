package com.siit.finalproject._controller;

import com.siit.finalproject.booking.model.DTO.*;
import com.siit.finalproject.booking.service.BookingService;
import com.siit.finalproject.userAccounts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetUserBookingsDTO> getBookingsForUser(@RequestParam Integer id) {
        return bookingService.getBookingsForUser(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetRestaurantBookingsDTO> getBookingsForRestaurant(@RequestParam Integer id) {
        return bookingService.getBookingsForRestaurant(id);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookingDTO> addBooking(@RequestBody PostBookingDTO bookingDTO) {
        GetBookingDTO response = bookingService.addBooking(bookingDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(response)
                .toUri();
        return ResponseEntity.created(uri)
                .body(response);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookingDTO> editBooking(@RequestBody EditBookingDTO editBookingDTO) {
        GetBookingDTO getBookingDTO = bookingService.editBooking(editBookingDTO);
        return ResponseEntity.ok()
                .body(getBookingDTO);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<GetBookingDTO> deleteBooking(@RequestParam Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }


}

