package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto updatePost(String id, PostDto postDto) throws PostNotFound {
        Post post = postRepository.find(PostId.of(id));

        post.update(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent())
        );

        return new PostDto(post);
    }
}
