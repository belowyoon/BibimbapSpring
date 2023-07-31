package com.springboot.Domain.posts;

import com.springboot.Domain.BaseTimeEntity;
import com.springboot.Domain.likes.Likes;
import com.springboot.Domain.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @OneToMany(mappedBy = "post")
    List<Likes> likes = new ArrayList<>();

    @Builder
    public Posts(String title, String content, String author, Member member) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
