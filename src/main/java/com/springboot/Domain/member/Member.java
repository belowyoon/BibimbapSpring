package com.springboot.Domain.member;

import com.springboot.Domain.likes.Likes;
import com.springboot.Domain.posts.Posts;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "member")
    List<Posts> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Likes> likes = new ArrayList<>();

    @Builder
    public Member(String name, String email, String picture, Role role, List<Posts> posts, List<Likes> likes) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.posts = posts;
        this.likes = likes;
    }

    public Member update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
