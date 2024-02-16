package org.api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meals", schema = "atopycare_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long mealId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "meal_date", unique = true)
    private Date mealDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal")
    private List<MenuEntity> menus;

    public MealEntity(UserEntity user, Date mealDate) {
        this.user = user;
        this.mealDate = mealDate;
    }

    public static MealEntity of(UserEntity user, Date mealDate) {
        return new MealEntity(user, mealDate);
    }
}
