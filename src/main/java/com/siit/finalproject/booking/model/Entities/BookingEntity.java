package com.siit.finalproject.booking.model.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "BookingEntity")
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booking_date")
    @Builder.Default
    private LocalDateTime bookingDate = LocalDateTime.now();

    @Column(name = "status")
    @Builder.Default
    private String status = "new";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
//    @JsonBackReference
    @JsonManagedReference
    private RestaurantsEntity restaurantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonBackReference
    @JsonManagedReference
    private UsersEntity userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookingEntity that = (BookingEntity) o;
        return Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(userId, that.userId) && Objects.equals(bookingDate, that.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingDate, restaurantId, userId);
    }


}
