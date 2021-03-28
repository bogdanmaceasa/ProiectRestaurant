package com.siit.finalproject.userAccounts.model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.siit.finalproject.booking.model.BookingEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "UserEntity")
@Table(name = "users")
public class UsersEntity {

    private static RestaurantRepository restaurantRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String password;

    @Column(name = "user_type")
    private int userType;

    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
//    @JsonManagedReference
    private List<BookingEntity> bookings = new ArrayList<>();


    public void addBooking(RestaurantsEntity restaurantsEntity) {
        BookingEntity bookingEntity = BookingEntity.builder()
                                                    .restaurantId(restaurantRepository.findById(restaurantsEntity.getId()).get())
                                                    .userId(this)
                                                    .build();
        bookings.add(bookingEntity);
    }

    public void removeTag(RestaurantsEntity restaurantsEntity) {
        for (Iterator<BookingEntity> iterator = bookings.iterator();
             iterator.hasNext(); ) {
            BookingEntity bookingEntity = iterator.next();

            if (bookingEntity.getUserId().equals(this) &&
                    bookingEntity.getRestaurantId().equals(restaurantsEntity)) {
                iterator.remove();
                bookingEntity.setUserId(null);
                bookingEntity.setRestaurantId(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity tag = (UsersEntity) o;
        return Objects.equals(name, tag.getName()) && Objects.equals(email, tag.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,email);
    }

}
