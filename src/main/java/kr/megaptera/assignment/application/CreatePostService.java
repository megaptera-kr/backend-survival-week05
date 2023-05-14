package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CreatePostDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(CreatePostDto createPostDto) {
        Post post = new Post(
            createPostDto.getTitle(),
            createPostDto.getAuthor(),
            createPostDto.getContent()
        );

        postRepository.save(post);
        return new PostDto(post);
    }
}
