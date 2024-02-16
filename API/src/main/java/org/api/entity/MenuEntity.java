package org.api.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "menus", schema = "atopycare_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private MealEntity meal;

    @Column(name = "food_name")
    private String foodMenu;

    @Column(name = "food_size")
    private int foodSize;

    @Column(name = "meal_time", nullable = false)
    private LocalTime mealTime;

    @Column(name = "remark")
    private String remark;

    public MenuEntity(MealEntity meal, String foodMenu, int foodSize, LocalTime mealTime,  String remark) {
        this.meal = meal;
        this.foodMenu = foodMenu;
        this.foodSize = foodSize;
        this.mealTime = mealTime;
        this.remark = remark;
    }

    public static MenuEntity of(MealEntity meal, String foodMenu, int foodSize, LocalTime mealTime, String remark) {
        return new MenuEntity(meal, foodMenu, foodSize, mealTime, remark);
    }
}

