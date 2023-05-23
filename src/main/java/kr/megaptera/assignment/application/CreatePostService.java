package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostDto postDto) {
        Post post = new Post(
                postDto.getTitle(),
                postDto.getAuthor(),
                MultilineText.of(postDto.getContent())
        );

        postRepository.save(post);

        return new PostDto(post);
    }
}
