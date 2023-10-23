package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> findAll() {
        return this.postRepository.findAll().stream().map(PostDto::of).toList();
    }
}
