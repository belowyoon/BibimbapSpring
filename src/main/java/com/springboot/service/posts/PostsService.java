package com.springboot.service.posts;

import com.springboot.Domain.member.Member;
import com.springboot.Domain.member.MemberRepository;
import com.springboot.Domain.posts.Posts;
import com.springboot.Domain.posts.PostsRepository;
import com.springboot.security.dto.SessionUser;
import com.springboot.web.dto.PostsListResponseDto;
import com.springboot.web.dto.PostsResponseDto;
import com.springboot.web.dto.PostsSaveRequestDto;
import com.springboot.web.dto.PostsUpdateRequestDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private  final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts posts = requestDto.toEntity();
        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        Member member = memberRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 email을 가진 member가 없습니다. id=" + user.getEmail()));
        posts.setMember(member);

        return postsRepository.save(posts).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional
    public boolean checkSessionUserWithPostMember(Long postsId, SessionUser user) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 posts가 없습니다" + postsId));

        System.out.println(posts.getMember().getEmail());
        System.out.println(user.getEmail());

        try{
            if(!posts.getMember().getEmail().equals(user.getEmail()))
                throw new IllegalArgumentException();
        }catch (Exception e)
        {
            System.out.println("글 작성자 본인만 글을 수정할 수 있습니다");
            return false;
        }
        return true;
    }

}
