package com.springboot.web.dto;

import com.springboot.Domain.likes.Likes;
import com.springboot.Domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    private int likeCount;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.likeCount = entity.getLikes().size();
    }
}
