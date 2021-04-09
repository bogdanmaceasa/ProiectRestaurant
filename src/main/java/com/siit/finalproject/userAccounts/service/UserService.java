package com.siit.finalproject.userAccounts.service;

import com.siit.finalproject.exceptions.UserNotFoundException;
import com.siit.finalproject.security.jwt.JwtUtils;
import com.siit.finalproject.security.payload.request.LoginRequest;
import com.siit.finalproject.security.payload.request.SignupRequest;
import com.siit.finalproject.security.payload.response.JwtResponse;
import com.siit.finalproject.security.payload.response.MessageResponse;
import com.siit.finalproject.security.service.UserDetailsImpl;
import com.siit.finalproject.userAccounts.mapper.UserEntityToDTOMap;
import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import com.siit.finalproject.userAccounts.model.Entities.RoleEnum;
import com.siit.finalproject.userAccounts.model.Entities.RolesEntity;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import com.siit.finalproject.userAccounts.repository.RolesRepository;
import com.siit.finalproject.userAccounts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserEntityToDTOMap userEntityToDTOMap;
    private final AuthenticationManager authenticationManager;
    private final RolesRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }


    public JwtResponse signin(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername(),
                usersRepository.findByUsername(loginRequest.getUsername())
                        .orElseThrow(()-> new UserNotFoundException("Username not found!"))
                        .getRoles());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

    }

    public ResponseEntity<MessageResponse> signup (SignupRequest signUpRequest) {
        if (usersRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (usersRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UsersEntity user = UsersEntity.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        Set<String> strRoles = signUpRequest.getRole();
        Set<RolesEntity> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            RolesEntity userRole = roleRepository.findByRole(RoleEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RolesEntity adminRole = roleRepository.findByRole(RoleEnum.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        RolesEntity userRole = roleRepository.findByRole(RoleEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        usersRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}
