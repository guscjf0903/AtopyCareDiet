package org.api.service;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.api.entity.LoginEntity;
import org.api.entity.MealEntity;
import org.api.entity.MenuEntity;
import org.api.entity.UserEntity;
import org.api.repository.MealRepository;
import org.api.repository.MenuRepository;
import org.core.dto.MenuPostDto;
import org.core.dto.MenuPostDto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostMenuService {
    private final MealRepository mealRepository;
    private final MenuRepository menuRepository;
    private final LoginService loginService;

    @Transactional
    public Date postMenu(MenuPostDto postMenuDto) {
        UserEntity userEntity = getUserData(postMenuDto);

        return DuplicateAndSaveMealDate(userEntity, postMenuDto);
    }

    private Date DuplicateAndSaveMealDate(UserEntity userEntity, MenuPostDto postMenuDto) { //meal 테이블에 겹치는 날짜의 menu데이터 확인
        Date menuDate = postMenuDto.getMenuDate();
        MealEntity savedMeal;
        if(!mealRepository.existsByMealDate(menuDate)) {
            savedMeal = mealRepository.save(MealEntity.of(userEntity,postMenuDto.getMenuDate()));

        } else {
            savedMeal = mealRepository.findByMealDate(postMenuDto.getMenuDate());
        }
        saveMenus(savedMeal, postMenuDto);

        return savedMeal.getMealDate();
    }

    private void saveMenus(MealEntity mealEntity, MenuPostDto postMenuDto) {
        for(MenuDTO menu :postMenuDto.getMenuList()) {
            menuRepository.save(MenuEntity.of(mealEntity, menu.getMenuName(),
                    menu.getMenuAmount(), postMenuDto.getMenuTime(), postMenuDto.getRemark()));
        }
    }

    private UserEntity getUserData(MenuPostDto postMenuDto) {
        LoginEntity loginEntity = loginService.validateLoginId(postMenuDto.getLoginToken());

        return loginEntity.getUser();
    }


}
