package com.siit.finalproject.userAccounts.model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.siit.finalproject.booking.model.Entities.BookingEntity;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.restaurantEntries.repository.RestaurantRepository;
import org.hibernate.annotations.Cache;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "UserEntity")
@Table(name = "users")
public class UsersEntity {

    private static RestaurantRepository restaurantRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Builder.Default
    private String name="TEST";

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolesEntity> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
//    @JsonManagedReference
    @JsonBackReference
    @Builder.Default
    private Set<BookingEntity> bookings = new HashSet<>();


    public void addBooking(RestaurantsEntity restaurantsEntity) {
        BookingEntity bookingEntity = BookingEntity.builder()
                .restaurantId(restaurantRepository.findById(restaurantsEntity.getId()).get())
                .userId(this)
                .build();
        bookings.add(bookingEntity);
    }

    public void removeBooking(RestaurantsEntity restaurantsEntity) {
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
        return Objects.hash(name, email);
    }

}
