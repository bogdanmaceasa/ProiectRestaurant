package com.siit.finalproject.booking.repository;

import com.siit.finalproject.booking.model.BookingEntity;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {


    List<BookingEntity> findAllByUserId(UsersEntity usersEntity);

}
