package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.exceptions.posts.PostNotFound;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto delete(String postId) {
        Post postToBeRemoved = this.postRepository.find(postId);

        if (postToBeRemoved == null) {
            throw new PostNotFound();
        }

        this.postRepository.remove(postId);

        return PostDto.of(postToBeRemoved);
    }
}
