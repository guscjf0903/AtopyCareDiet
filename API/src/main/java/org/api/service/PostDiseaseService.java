package org.api.service;

import static org.api.exception.ErrorCodes.DUPLICATE_DISEASEDATE_ERROR;
import static org.api.exception.ErrorCodes.PASSWORD_DISMATCH;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.api.entity.DiseaseEntity;
import org.api.entity.LoginEntity;
import org.api.entity.UserEntity;
import org.api.exception.CustomException;
import org.api.repository.DiseaseRepository;
import org.core.dto.DiseasePostDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostDiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final LoginService loginService;


    @Transactional
    public Date postDisease(DiseasePostDto postDiseaseDto) {
        UserEntity userEntity = getUserData(postDiseaseDto);
        return DuplicateDateAndSaveDisease(userEntity, postDiseaseDto);
    }

    private Date DuplicateDateAndSaveDisease(UserEntity userEntity, DiseasePostDto postDiseaseDto) {
        if(diseaseRepository.existsByDiseaseDate(postDiseaseDto.getDiseaseDate())) {
            throw new CustomException(DUPLICATE_DISEASEDATE_ERROR);
        }
        System.out.println("테스트2");

        DiseaseEntity diseaseEntity = DiseaseEntity.of(userEntity, postDiseaseDto);

        return diseaseRepository.save(diseaseEntity).getDiseaseDate();
    }

    private UserEntity getUserData(DiseasePostDto postDiseaseDto) {
        System.out.println(postDiseaseDto.getLoginToken());
        LoginEntity loginEntity = loginService.validateLoginId(postDiseaseDto.getLoginToken());
        return loginEntity.getUser();
    }
}
