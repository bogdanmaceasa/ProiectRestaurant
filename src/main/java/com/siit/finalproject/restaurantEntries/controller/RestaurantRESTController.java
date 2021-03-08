package com.siit.finalproject.restaurantEntries.controller;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantDTO;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantDTO> getAllRestaurants(){
        return restaurantsService.getAllRestaurants();
    }


    //http://localhost:8080/restaurants/query?name=dei
    @GetMapping(value = "/query",produces = MediaType.APPLICATION_JSON_VALUE )
    public List<RestaurantDTO> searchRestaurantByName(@RequestParam String name){
        return restaurantsService.searchRestaurantByName(name);
    }
//    TO TRY ->
//    public String getFoos(@RequestParam Optional<String> id){
//        return "ID: " + id.orElseGet(() -> "not provided");


    @PostMapping(value="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO createdRestaurant = restaurantsService.addRestaurant(restaurantDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
                .buildAndExpand(createdRestaurant.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(createdRestaurant);
    }

    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO updatedRestaurant = restaurantsService.updateRestaurant(restaurantDTO);

        return ResponseEntity.ok()
                .body(updatedRestaurant);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<RestaurantDTO> deleteRestaurant(@RequestParam Integer id){
        Optional<RestaurantDTO> output = restaurantsService.deleteRestaurant(id);

        if ( output.isPresent())
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

}
