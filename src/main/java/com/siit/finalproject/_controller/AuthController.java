package com.siit.finalproject._controller;


import com.siit.finalproject.exceptions.UserNotFoundException;
import com.siit.finalproject.security.payload.request.LoginRequest;
import com.siit.finalproject.security.payload.request.SignupRequest;
import com.siit.finalproject.security.payload.response.JwtResponse;
import com.siit.finalproject.userAccounts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        JwtResponse authResponse = userService.signin(loginRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        return userService.signup(signUpRequest);
    }
}
