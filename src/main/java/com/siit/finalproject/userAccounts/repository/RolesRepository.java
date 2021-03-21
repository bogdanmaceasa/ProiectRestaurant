package com.siit.finalproject.userAccounts.repository;


import com.siit.finalproject.userAccounts.model.Entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
}
