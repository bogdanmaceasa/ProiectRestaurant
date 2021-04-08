package com.siit.finalproject._controller;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.specialities.model.DTO.SpecialitiesDTO;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
import com.siit.finalproject.specialities.service.SpecialitiesService;
import com.siit.finalproject.userAccounts.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


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

    //http://localhost:8080/restaurants/query?name=dei
//    @GetMapping(value = "/query",produces = MediaType.APPLICATION_JSON_VALUE )
//    public List<RestaurantGetDTO> searchRestaurantByName(@RequestParam String name){
//        return restaurantsService.searchRestaurantByName(name);
//    }
//    TO TRY ->
//    public String getFoos(@RequestParam Optional<String> id){
//        return "ID: " + id.orElseGet(() -> "not provided");


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantGetDTO> addRestaurant(@RequestBody RestaurantPostDTO restaurantPostDTO) {
        RestaurantGetDTO createdRestaurant = restaurantsService.addRestaurant(restaurantPostDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(createdRestaurant.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(createdRestaurant);
    }

    @PostMapping(value = "/addbulk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantGetDTO>> addRestaurantsBulk(@RequestBody List<RestaurantPostDTO> restaurantPostDTOList) {
        List<RestaurantGetDTO> restaurantGetDTOList = restaurantsService.addRestaurantsBulk(restaurantPostDTOList);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(restaurantGetDTOList)
                .toUri();
        return ResponseEntity.created(uri)
                .body(restaurantGetDTOList);
    }


    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantGetDTO> updateRestaurant(@RequestBody RestaurantPostDTO restaurantPostDTO) {
        RestaurantGetDTO updatedRestaurant = restaurantsService.updateRestaurant(restaurantPostDTO);
        return ResponseEntity.ok()
                .body(updatedRestaurant);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<RestaurantGetDTO> deleteRestaurant(@RequestParam Integer id) {
        restaurantsService.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }

}
