package com.siit.finalproject.details.repository;


import com.siit.finalproject.details.model.Entity.DetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailsRepository extends JpaRepository<DetailsEntity, Integer> {

    Optional<DetailsEntity> findByDetails(String string);
}
