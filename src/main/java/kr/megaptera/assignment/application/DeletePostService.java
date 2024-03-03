package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeletePostService {

    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto delete(String postId) {
        Optional<Post> post = postRepository.find(PostId.of(postId));
        if (post.isEmpty()) {
            throw new PostNotFound();
        }

        postRepository.delete(PostId.of(postId));

        return new PostDto(post.get());
    }
}
