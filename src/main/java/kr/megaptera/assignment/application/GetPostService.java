package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class GetPostService {
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPostDto(String id) throws PostNotFound {
        Post post = postRepository.find(PostId.of(id));

        return new PostDto(post);
    }
}
