package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto delete(String id) {
        Post post = postRepository.find(PostId.of(id));
        postRepository.delete(PostId.of(id));
        return new PostDto(post);
    }
}
