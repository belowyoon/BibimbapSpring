package com.springboot.service.likes;

import com.springboot.Domain.likes.Likes;
import com.springboot.Domain.likes.LikesRepository;
import com.springboot.Domain.member.Member;
import com.springboot.Domain.member.MemberRepository;
import com.springboot.Domain.posts.Posts;
import com.springboot.Domain.posts.PostsRepository;
import com.springboot.security.dto.SessionUser;
import com.springboot.web.dto.MemberResponseDto;
import com.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public Long save(Long memberId, Long postsId) {

        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postsId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없습니다. id=" + memberId));

        posts.updateLikeCount(posts.getLikeCount()+1);
        Likes like = Likes.builder()
                .post(posts)
                .member(member)
                .likeDate(LocalDateTime.now().toLocalDate())
                .build();

        return likesRepository.save(like).getId();
    }

    @Transactional
    public void delete(Long id) {
        Likes like = likesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다. id=" + id));

        likesRepository.delete(like);
    }

    @Transactional
    public MemberResponseDto findMember(Long id) {
        Likes like = likesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다. id=" + id));
        Member entity = like.getMember();
        System.out.println(entity.getEmail());
        return new MemberResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMember(Long postId) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));

        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        for (Likes likes : posts.getLikes()) {
            memberResponseDtoList.add(new MemberResponseDto(likes.getMember()));
        }
        return memberResponseDtoList;
    }
}
