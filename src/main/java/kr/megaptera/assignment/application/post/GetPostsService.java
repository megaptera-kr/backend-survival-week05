package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
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
