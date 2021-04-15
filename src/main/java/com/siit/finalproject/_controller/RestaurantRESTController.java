package com.siit.finalproject._controller;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.specialities.model.DTO.SpecialitiesDTO;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
import com.siit.finalproject.specialities.service.SpecialitiesService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRESTController {

    private final RestaurantsService restaurantsService;
    private final SpecialitiesService specialitiesService;

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantGetDTO getRestaurantById(@RequestParam Integer id) {
        return restaurantsService.findByID(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantGetDTO> getAllRestaurants() {
        return restaurantsService.getAllRestaurants();
    }

    @GetMapping(value = "/specialities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SpecialitiesDTO> getAllSpecialities() {
        return specialitiesService.getAllSpecialities();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantGetDTO addRestaurant(@RequestBody RestaurantPostDTO restaurantPostDTO) {
        return restaurantsService.addRestaurant(restaurantPostDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addbulk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantGetDTO> addRestaurantsBulk(@RequestBody List<RestaurantPostDTO> restaurantPostDTOList) {
        return restaurantsService.addRestaurantsBulk(restaurantPostDTOList);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantGetDTO updateRestaurant(@RequestBody RestaurantPostDTO restaurantPostDTO) {
        return restaurantsService.updateRestaurant(restaurantPostDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/delete")
    public void deleteRestaurant(@RequestParam Integer id) {
         restaurantsService.deleteRestaurant(id);
    }

}
