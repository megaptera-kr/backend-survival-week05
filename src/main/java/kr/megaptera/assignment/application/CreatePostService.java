package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.PostConverter;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDetailDto createPost(PostCreateDto dto) {
        Post post = PostConverter.toPost(dto);
        Post newPost = postRepository.save(post);
        return PostConverter.toPostDetailDto(newPost);
    }
}
