package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto update(String postId, PostUpdateDto postUpdateDto) {
        Post post = postRepository.find(postId);
        post.update(
                PostTitle.of(postUpdateDto.getTitle()),
                MultiLineText.of(postUpdateDto.getContent())
        );
        return new PostDto(post);
    }
}
