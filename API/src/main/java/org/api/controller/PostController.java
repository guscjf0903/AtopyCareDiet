package org.api.controller;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.api.service.PostMenuService;
import org.core.dto.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class PostController {
    private final PostMenuService postMenuService;

    @PostMapping("/new")
    public ResponseEntity<Map<String, Long>> postMenu(@RequestBody @Valid PostDto postMenuDto) {
        Long postId = postMenuService.postMenu(postMenuDto);
        Map<String, Long> response = new HashMap<>();
        response.put("postId", postId);

        return ResponseEntity.ok(response);
    }

}
