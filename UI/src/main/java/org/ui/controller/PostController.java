package org.ui.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.core.dto.DiseasePostDto;
import org.core.dto.MenuPostDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/new-post")
public class PostController {
    @GetMapping("/menu")
    public String showPostMenuForm() {
        return "postMenuAndDisease";
    }

    @PostMapping("/menu")
    public ResponseEntity<Map> postMenu(@RequestBody MenuPostDto postMenuDto, @Value("${api.url}") String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> postMenuStatus = restTemplate.postForEntity(url + "/new-post/menu", postMenuDto, Map.class);

        return postMenuStatus;
    }

    @GetMapping("/disease")
    public String showPostDiseaseForm() {
        return "postMenuAndDisease";
    }

    @PostMapping("/disease")
    public ResponseEntity<Map> postDisease(@RequestBody DiseasePostDto postDiseaseDto, @Value("${api.url}") String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> postDiseaseStatus = restTemplate.postForEntity(url + "/new-post/disease", postDiseaseDto, Map.class);

        return postDiseaseStatus;
    }



//    @GetMapping("/{postId}")
//    public String showMenuDetailForm() {
//        return "menuDetail";
//    }
//
//    @PostMapping("/{postId}")
//    public ResponseEntity<String> getMenu(@PathVariable Long postId, @Value("${api.url}") String url) {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> getMenuDetail = restTemplate.getForEntity(url + "/menu/" + postId, String.class);
//
//        return getMenuDetail;
//    }


}
