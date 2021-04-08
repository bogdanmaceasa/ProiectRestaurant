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
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserEntityToDTOMap userEntityToDTOMap;

    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }


}
