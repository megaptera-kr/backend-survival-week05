package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class UpdatePostService {

    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto update(String id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.find(id);
        post.update(
                postUpdateDto.getTitle(),
                PostContent.of(postUpdateDto.getContent())
        );
        return new PostDto(post);
    }
}
