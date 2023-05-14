package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GetPostsService {
    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(PostDto::new).toList();
    }
}
