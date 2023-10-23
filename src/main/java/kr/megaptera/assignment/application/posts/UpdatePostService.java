package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.dtos.posts.UpdatePostDto;
import kr.megaptera.assignment.exceptions.posts.PostNotFound;
import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto update(String postId, UpdatePostDto updatePostDto) {
        Post postToBeUpdated = this.postRepository.find(postId);

        if (postToBeUpdated == null) {
            throw new PostNotFound();
        }

        postToBeUpdated.update(updatePostDto);

        return PostDto.of(postToBeUpdated);
    }
}
