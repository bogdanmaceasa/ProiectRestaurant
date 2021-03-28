package com.siit.finalproject.booking.service;

import com.siit.finalproject.booking.model.BookingEntity;
import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import com.siit.finalproject.userAccounts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BookingService {

    private final RestaurantRepository restaurantRepository;
    private final BookingRepository bookingRepository;
    private final UsersRepository usersRepository;

    public List<BookingEntity> getBookingsForUser (int id){
        UsersEntity usersEntity = usersRepository.findById(id).get();

        return bookingRepository.findAllByUserId(usersEntity);
    }

}
