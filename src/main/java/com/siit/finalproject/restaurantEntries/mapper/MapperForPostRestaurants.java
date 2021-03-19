package com.siit.finalproject.restaurantEntries.mapper;

import com.siit.finalproject.booking.repository.BookingRepository;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.model.Entities.*;
import com.siit.finalproject.restaurantEntries.repository.*;
import com.siit.finalproject.specialities.repository.SpecialitiesRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Builder
@RequiredArgsConstructor
@Component
public class MapperForPostRestaurants {

    private final AddressRepository addressRepository;
    private final DetailsRepository detailsRepository;
    private final SpecialitiesRepository specialitiesRepository;
    private final BookingRepository bookingRepository;
    private final RestaurantSpecialitiesRepository restaurantSpecialitiesRepository;
    private final RestaurantRepository restaurantRepository;


    public RestaurantsEntity mapPostDTOToEntity(RestaurantPostDTO restaurantPostDTO) {
//        HashSet<RestaurantSpecialitiesEntity> set = restaurantPostDTO.getSpecialities()
//                                                                        .stream()
//                                                                        .map(s -> specialitiesRepository.findById(s).get())
//                                                                        .map( s-> RestaurantSpecialitiesEntity.builder()
//                                                                                                                .specialitiesEntity(s)
//                                                                                                                .restaurantsEntity(restaurantJoinRepository.findById(restaurantPostDTO.getId()).get())
//                                                                                                                .build()
//                                                                        )
//                                                                        .collect(Collectors.toCollection(HashSet::new));
        return RestaurantsEntity.builder()
                .id(restaurantPostDTO.getId())
                .name(restaurantPostDTO.getName())
                .address(addressRepository.findById(restaurantPostDTO.getAddressId()).get())
                .details(detailsRepository.findById(restaurantPostDTO.getDetailsId()).get())
                .booking(bookingRepository.findById(restaurantPostDTO.getBookingId()).get())
                .build();
    }
}
