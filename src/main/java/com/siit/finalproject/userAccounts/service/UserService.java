package com.siit.finalproject.userAccounts.service;

import com.siit.finalproject.userAccounts.mapper.UserEntityToDTOMap;
import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import com.siit.finalproject.userAccounts.repository.RolesRepository;
import com.siit.finalproject.userAccounts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserEntityToDTOMap userEntityToDTOMap;
    private final static int HASH = 123;



    public UserDTO checkUser(String email, String password){
        UsersEntity user = usersRepository.findUsersEntityByEmail(email);
        int value = user.getPassword().hashCode()*HASH;
        if(String.valueOf(value).equals(password))
            return userEntityToDTOMap.mapFromEntityToDTO(user);

        return null;
    }


}
