package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePostService {

    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto update(String postId, PostUpdateDto postUpdateDto) {
        Optional<Post> post = postRepository.find(PostId.of(postId));
        if (post.isEmpty()) {
            throw new PostNotFound();
        }

        Post update = new Post(
                post.get().id(),
                post.get().title(),
                post.get().author(),
                MultilineText.of(postUpdateDto.getContent())
        );

        postRepository.save(update);

        return new PostDto(update);
    }
}
