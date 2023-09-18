package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dto.PostDto;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.model.PostId;
import kr.megaptera.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto deletePost(String id) {
        Post post = postRepository.remove(PostId.of(id));
        return new PostDto(post);
    }
}
