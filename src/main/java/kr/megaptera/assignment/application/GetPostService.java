package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dto.PostDto;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.model.PostId;
import kr.megaptera.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto getPostDto(String id) {
        Post post = postRepository.findOne(PostId.of(id));
        return new PostDto(post);
    }
}
