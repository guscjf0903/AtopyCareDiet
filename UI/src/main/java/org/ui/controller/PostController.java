package org.ui.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.core.dto.PostDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu")
public class PostController {
    @GetMapping("/new")
    public String showPostMenuForm() {
        return "postMenu";
    }

    @PostMapping("/new")
    public ResponseEntity<Map> postMenu(@RequestBody PostDto postMenuDto, @Value("${api.url}") String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> postMenuStatus = restTemplate.postForEntity(url + "/menu/new", postMenuDto, Map.class);

        return postMenuStatus;
    }

    @GetMapping("/{postId}")
    public String showMenuDetailForm() {
        return "menuDetail";
    }

    @PostMapping("/{postId}")
    public ResponseEntity<String> getMenu(@PathVariable Long postId, @Value("${api.url}") String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getMenuDetail = restTemplate.getForEntity(url + "/menu/" + postId, String.class);

        return getMenuDetail;
    }


}
