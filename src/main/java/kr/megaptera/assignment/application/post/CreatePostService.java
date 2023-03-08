package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostService {

    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto create(PostCreateDto postCreateDto) {
        Post post = postRepository.save(postCreateDto);
        return new PostDto(post);
    }
}
