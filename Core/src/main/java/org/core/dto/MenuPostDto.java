package org.core.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuPostDto {
    private Date menuDate;
    private LocalTime menuTime;
    private String remark;
    private List<MenuDTO> menuList;

    private Long loginToken;

    @Getter
    @Setter
    public static class MenuDTO {
        @NotBlank(message = "메뉴 이름을 입력해주세요.")
        private String menuName;
        @NotBlank(message = "식사 수량(g)을 입력해주세요.")
        private int menuAmount;
    }
}
