package com.siit.finalproject.userAccounts.mapper;

import com.siit.finalproject.userAccounts.model.DTO.UserDTO;
import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToDTOMap {

    public UserDTO mapFromEntityToDTO(UsersEntity usersEntity) {
        return UserDTO.builder()
                .id(usersEntity.getId())
                .email(usersEntity.getEmail())
                .name(usersEntity.getName())
                .build();
    }
}
