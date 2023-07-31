package com.springboot.web.dto;

import com.springboot.Domain.member.Member;
import com.springboot.Domain.member.Role;
import com.springboot.Domain.posts.Posts;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;

    private String name;

    private String email;

    private String picture;

    private Role role;

    @Builder
    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.role = member.getRole();
    }

}
