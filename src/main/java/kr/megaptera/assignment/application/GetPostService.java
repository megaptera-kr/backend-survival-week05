package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.PostConverter;
import kr.megaptera.assignment.dtos.PostDetailDto;
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

    public PostDetailDto getPost(String postId) {
        Post post = postRepository.find(PostId.of(postId));
        return PostConverter.toPostDetailDto(post);
    }
}
