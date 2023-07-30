package com.springboot.web.dto;

import com.springboot.Domain.member.Member;
import com.springboot.Domain.posts.Posts;
import io.swagger.v3.oas.models.info.Contact;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;

    private Member member;
    private String title;
    private String content;
    private String author;


    @Builder
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.member = entity.getMember();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
