package com.siit.finalproject.specialities.repository;


import com.siit.finalproject.specialities.model.Entities.SpecialitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialitiesRepository extends JpaRepository<SpecialitiesEntity, Integer> {

//    @Query(value = " select * from specialities u where u.id =?", nativeQuery = true)
    SpecialitiesEntity findTypeById(int id);
    Optional<SpecialitiesEntity> findByType(String type);

}
