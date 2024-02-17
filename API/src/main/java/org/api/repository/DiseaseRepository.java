package org.api.repository;

import java.util.Date;
import org.api.entity.DiseaseEntity;
import org.api.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<DiseaseEntity, Long> {
    boolean existsByDiseaseDate(Date diseaseDate);
}
