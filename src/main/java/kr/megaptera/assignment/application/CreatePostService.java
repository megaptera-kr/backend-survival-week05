package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CreatePostResponse;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CreatePostResponse createPost(Post post) {
        Post result = postRepository.save(post);

        return new CreatePostResponse(result.getId().toString(),
                result.getTitle(), result.getAuthor(), result.getContent());
    }
}
