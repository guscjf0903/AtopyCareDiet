package org.api.repository;

import java.util.Date;
import org.api.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {
    boolean existsByMealDate(Date mealDate);

    MealEntity findByMealDate(Date mealDate);
}
