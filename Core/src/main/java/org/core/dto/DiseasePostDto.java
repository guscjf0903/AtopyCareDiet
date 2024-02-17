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
public class DiseasePostDto {
    private Date diseaseDate;
    private int diseaseStep;
    private String diseaseRemark;
    private Long loginToken;
}
