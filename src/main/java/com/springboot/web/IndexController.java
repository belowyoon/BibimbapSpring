package com.springboot.web;

import com.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    // "/"로 GET 요청이 들어오면 index.mustache를 띄워줌
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // "/posts/save"로 GET 요청이 들어오면 posts-save.mustache를 띄워줌
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
