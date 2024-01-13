package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.PostConverter;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDetailDto deletePost(String id) {
        return PostConverter.toPostDetailDto(postRepository.delete(PostId.of(id)));
    }
}
