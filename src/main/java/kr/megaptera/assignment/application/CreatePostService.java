package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostCreateDto;
import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostService {

    private final PostRepository postRepository;

    public PostDto createPost(PostCreateDto postCreateDto) {
        // 각 과정을 나누어서 써주어야 테스트 시 이상 없음
        Post post = new Post(postCreateDto);
        postRepository.save(new Post(postCreateDto));
        return new PostDto(post);
    }
}
