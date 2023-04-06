package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto findById(String id) {
        Post post = postRepository.findById(PostId.of(id));
        return new PostDto(post);
    }
}
