package com.springboot.Domain.likes;

import com.springboot.Domain.BaseTimeEntity;
import com.springboot.Domain.member.Member;
import com.springboot.Domain.posts.Posts;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts post;

    private LocalDate likeDate;

    @Builder
    public Likes (Posts post, Member member, LocalDate likeDate) {
        this.post = post;
        this.member = member;
        this.likeDate = likeDate;
    }

}
