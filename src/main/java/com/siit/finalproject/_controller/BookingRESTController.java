package com.siit.finalproject._controller;

import com.siit.finalproject.booking.model.BookingEntity;
import com.siit.finalproject.booking.service.BookingService;
import com.siit.finalproject.userAccounts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingRESTController {

    private final BookingService bookingService;
    private final UserService userService;

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingEntity> getBookingsForUser(@RequestParam int id) {
        return bookingService.getBookingsForUser(id);
    }

}

