package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.controllers.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto updatePost(String id, PostUpdateDto updateDto) {
        Post post = postRepository.findById(PostId.of(id));
        post.update(updateDto.getTitle(),
                new MultiLineText(updateDto.getContent()));
        return new PostDto(post);
    }
}
