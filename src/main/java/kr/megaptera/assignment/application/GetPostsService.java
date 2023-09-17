package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dto.PostDto;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
