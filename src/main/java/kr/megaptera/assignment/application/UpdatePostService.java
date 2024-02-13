package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
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

    public PostDto update(String id, PostDto postDto) {
        Optional<Post> found = postRepository.findById(PostId.from(id));
        if (found.isEmpty()) {
            throw new PostNotFoundException("invalid post id");
        }
        Post updatedPost = new Post(found.get(), postDto.getTitle(), MultilineText.from(postDto.getContent()));
        postRepository.save(updatedPost);

        return PostDto.from(updatedPost);
    }
}
