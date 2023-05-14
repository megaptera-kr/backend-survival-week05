package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.UpdatePostDto;
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

    public PostDto updatePost(String id, UpdatePostDto updatePostDto) {
        Post post = postRepository.findPost(PostId.of(id));

        post.update(
            updatePostDto.getTitle(),
            updatePostDto.getContent()
        );

        return new PostDto(post);
    }
}
