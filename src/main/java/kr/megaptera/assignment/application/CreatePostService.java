package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreatePostService {
    private PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostCreateDto postcreateDto) {
        Post post = new Post(
                postcreateDto.getTitle(),
                postcreateDto.getAuthor(),
                postcreateDto.getContent());

        postRepository.save(post);

        return new PostDto(post);
    }
}
