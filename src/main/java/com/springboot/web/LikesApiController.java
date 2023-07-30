package com.springboot.web;

import com.springboot.Domain.member.Member;
import com.springboot.service.likes.LikeService;
import com.springboot.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LikesApiController {

    @Autowired
    private final LikeService likeService;

    @PostMapping("/api/v1/like/{memberId}/{postsId}")
    public long save(@PathVariable Long memberId, @PathVariable Long postsId) {

        return likeService.save(memberId, postsId);
    }

    @DeleteMapping("/api/v1/likes/{id}")
    public Long delete(@PathVariable Long id) {
        likeService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/likes/{id}")
    public MemberResponseDto findMemberById(@PathVariable Long id) {
        return likeService.findMember(id);
    }

    @GetMapping("/api/v1/posts/likes/{postId}")
    public List<MemberResponseDto> findAllMember(@PathVariable Long postId) {
        return likeService.findAllMember(postId);
    }
}
