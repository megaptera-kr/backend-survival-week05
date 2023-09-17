package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dto.PostCreateDto;
import kr.megaptera.assignment.dto.PostDto;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private PostRepository postRepository;

    public CreatePostService() {
        this.postRepository = new PostRepository();
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post(postCreateDto);
        postRepository.save(post);

        return new PostDto(post);
    }
}
