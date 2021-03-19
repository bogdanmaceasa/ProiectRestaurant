package com.siit.finalproject.userAccounts.repository;


import com.siit.finalproject.userAccounts.model.Entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {

    public UsersEntity findUsersEntityByEmail(String email);


}
