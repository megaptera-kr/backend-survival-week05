package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.PostCreateDto;
import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                new MultiLineText(postCreateDto.getContent())
        );
        postRepository.createPost(post);
        return new PostDto(post);
    }
}
