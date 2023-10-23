package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto create(CreatePostDto createPostDto) {
        Post newPost = Post.of(createPostDto);

        this.postRepository.save(newPost);

        return PostDto.of(newPost);
    }
}
