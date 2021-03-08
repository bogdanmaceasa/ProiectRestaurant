package com.siit.finalproject.restaurantEntries.repository;

import com.siit.finalproject.booking.Booking;
import com.siit.finalproject.restaurantEntries.model.Entities.AddressEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.DetailsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.SpecialitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantsEntity,Integer> {

    List<RestaurantsEntity> findAllByNameIsContaining(String name);

    @Transactional
    @Modifying
    @Query(value = "update restaurants r set r.name =?2, r.address_id = ?3, r.type_id = ?4, r.details_id = ?5, r.booking_id = ?6  where r.id = ?1",nativeQuery = true)
    int updateRestaurant(Integer id,
                         String name,
                         @Param("address_id") AddressEntity addressId,
                         @Param("type_id") SpecialitiesEntity typeId,
                         @Param("details_id") DetailsEntity detailsId,
                         @Param("booking_id") Booking bookingId);


//    @Transactional
//    @Modifying
//    @Query(value = "update restaurants r set r.name =?2  where r.id = ?1",nativeQuery = true)
//    int updateRestaurant(Integer id, String name);

}
