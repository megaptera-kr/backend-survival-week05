package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.exceptions.posts.PostNotFound;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto find(String postId) {
        Post post = this.postRepository.find(postId);

        if (post == null) {
            throw new PostNotFound();
        }

        return PostDto.of(post);
    }
}
