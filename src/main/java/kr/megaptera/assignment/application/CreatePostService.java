package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.utils.PostUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import kr.megaptera.assignment.repositories.PostRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePostService {

    private final PostRepository postRepository;

    public PostDto createPost(PostDto postDto) {
        log.info("postDto: {}", postDto);
        Post post = Post.builder()
                .id(PostUtil.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .author(postDto.getAuthor())
                .build();
        postRepository.save(post);

        return new PostDto(post);
    }

}
