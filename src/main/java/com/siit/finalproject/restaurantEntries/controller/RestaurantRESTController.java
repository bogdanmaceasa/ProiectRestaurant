package com.siit.finalproject.restaurantEntries.controller;

import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantGetDTO;
import com.siit.finalproject.restaurantEntries.model.DTO.RestaurantPostDTO;
import com.siit.finalproject.restaurantEntries.service.RestaurantsService;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantGetDTO> getAllRestaurants(){
        return restaurantsService.getAllRestaurants();
    }


    //http://localhost:8080/restaurants/query?name=dei
//    @GetMapping(value = "/query",produces = MediaType.APPLICATION_JSON_VALUE )
//    public List<RestaurantGetDTO> searchRestaurantByName(@RequestParam String name){
//        return restaurantsService.searchRestaurantByName(name);
//    }
//    TO TRY ->
//    public String getFoos(@RequestParam Optional<String> id){
//        return "ID: " + id.orElseGet(() -> "not provided");


    @PostMapping(value="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantPostDTO> addRestaurant(@RequestBody RestaurantPostDTO restaurantPostDTO){
        RestaurantPostDTO createdRestaurant = restaurantsService.addRestaurant(restaurantPostDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
                .buildAndExpand(createdRestaurant.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(createdRestaurant);
    }

    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantGetDTO> updateRestaurant(@RequestBody RestaurantGetDTO restaurantGetDTO){
        RestaurantGetDTO updatedRestaurant = restaurantsService.updateRestaurant(restaurantGetDTO);

        return ResponseEntity.ok()
                .body(updatedRestaurant);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<RestaurantGetDTO> deleteRestaurant(@RequestParam Integer id){
        Optional<RestaurantGetDTO> output = restaurantsService.deleteRestaurant(id);

        if ( output.isPresent())
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

}
