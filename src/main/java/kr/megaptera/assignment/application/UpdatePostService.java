package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto update(String id, PostUpdateDto postUpdateDto){
        Post post = postRepository.find(PostId.of(id));

        post.update(postUpdateDto.getTitle(),postUpdateDto.getContent());
        return new PostDto(post);
    }
}
