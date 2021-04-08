package com.siit.finalproject.booking.repository;

import com.siit.finalproject.booking.model.Entities.BookingEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {


    Set<BookingEntity> findAllByUserId(UsersEntity usersEntity);

    Set<BookingEntity> findAllByRestaurantId(RestaurantsEntity restaurantsEntity);

//    int countAllByBookingDateEqualsAndAndRestaurantId(LocalDateTime date,RestaurantsEntity id);
    int countAllByBookingDateEqualsAndAndRestaurantId(LocalDateTime date,RestaurantsEntity id);
    int countAllByBookingDateBetweenAndRestaurantId(LocalDateTime date1,LocalDateTime date2, RestaurantsEntity id);
    int countAllByRestaurantId(RestaurantsEntity id);
    int countAllByRestaurantIdAndBookingDateBetween(RestaurantsEntity id,LocalDateTime date1,LocalDateTime date2);

}
