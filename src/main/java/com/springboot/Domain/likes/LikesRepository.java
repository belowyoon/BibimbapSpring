package com.springboot.Domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberIdAndPostsId(Long memberId, Long postsId);
}
