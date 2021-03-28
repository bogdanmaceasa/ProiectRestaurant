package com.siit.finalproject.booking.model;

import com.siit.finalproject.restaurantEntries.model.Entities.RestaurantsEntity;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="booking")
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

    @JoinColumn(name="restaurant_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RestaurantsEntity restaurantId;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UsersEntity userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookingEntity that = (BookingEntity) o;
        return Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(userId, that.userId) && Objects.equals(bookingDate,that.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId,userId,bookingDate);
    }


}
