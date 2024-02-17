package org.api.controller;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.api.service.PostDiseaseService;
import org.api.service.PostMenuService;
import org.core.dto.DiseasePostDto;
import org.core.dto.MenuPostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/new-post")
public class PostController {
    private final PostMenuService postMenuService;
    private final PostDiseaseService postDiseaseService;

    @PostMapping("/menu")
    public ResponseEntity<Map<String, Date>> postMenu(@RequestBody @Valid MenuPostDto postMenuDto) {
        Date postDate = postMenuService.postMenu(postMenuDto);
        Map<String, Date> response = new HashMap<>();
        response.put("postDate", postDate);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/disease")
    public ResponseEntity<Map<String, Date>> postDisease(@RequestBody @Valid DiseasePostDto postDiseaseDto) {
        Date postDate = postDiseaseService.postDisease(postDiseaseDto);
        Map<String, Date> response = new HashMap<>();
        response.put("postDate", postDate);

        return ResponseEntity.ok(response);
    }

}
