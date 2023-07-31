package com.springboot.service.likes;

import com.springboot.Domain.likes.Likes;
import com.springboot.Domain.likes.LikesRepository;
import com.springboot.Domain.member.Member;
import com.springboot.Domain.member.MemberRepository;
import com.springboot.Domain.posts.Posts;
import com.springboot.Domain.posts.PostsRepository;
import com.springboot.exception.LikeToggleException;
import com.springboot.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public Long save(Long memberId, Long postsId) {

        Optional<Likes> byMemberIdAndPostsId = likesRepository.findByMemberIdAndPostsId(memberId, postsId);
        if(byMemberIdAndPostsId.isPresent())
            return -1L;

        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postsId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없습니다. id=" + memberId));

        Likes likeEntity = Likes.builder()
                .post(posts)
                .member(member)
                .likeDate(LocalDateTime.now().toLocalDate())
                .build();

        return likesRepository.save(likeEntity).getId();
    }

    @Transactional
    public Long delete(Long memberId, Long postsId) {
        Optional<Likes> byMemberIdAndPostsId = likesRepository.findByMemberIdAndPostsId(postsId, memberId);
        if(!byMemberIdAndPostsId.isPresent())
            throw new LikeToggleException("좋아요가 안눌려있습니다.");

        Long deleteId = byMemberIdAndPostsId.get().getId();
        likesRepository.deleteById(deleteId);
        return deleteId;
    }

    @Transactional
    public MemberResponseDto findMember(Long id) {
        Likes like = likesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다. id=" + id));

        return MemberResponseDto.builder()
                .member(like.getMember())
                .build();
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberByPostsId(Long postId) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));

        return posts.getLikes().stream()
                .map(entity -> {
                    return MemberResponseDto.builder()
                            .member(entity.getMember())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
