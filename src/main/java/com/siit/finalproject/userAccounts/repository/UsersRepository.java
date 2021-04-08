package com.siit.finalproject.userAccounts.repository;


import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findUsersEntityByEmail(String email);

    Optional<UsersEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
