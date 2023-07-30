package com.springboot.web;

import com.springboot.security.dto.SessionUser;
import com.springboot.service.posts.PostsService;
import com.springboot.web.dto.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // "/"로 GET 요청이 들어오면 index.mustache를 띄워줌
    @GetMapping("/")
    public String index(Model model) { //추가된 부분
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    // "/posts/save"로 GET 요청이 들어오면 posts-save.mustache를 띄워줌
    @GetMapping("/posts/save")
    public String postsSave(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);

        SessionUser user = (SessionUser) httpSession.getAttribute("user");


        if(!postsService.checkSessionUserWithPostMember(id, user))
            return "/";

        model.addAttribute("post", postsService.findById(id));

        return "posts-update";
    }
}
