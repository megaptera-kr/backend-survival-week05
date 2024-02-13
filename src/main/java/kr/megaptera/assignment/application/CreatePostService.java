package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class CreatePostService {

    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto create(PostDto postDto) {
        Post post = new Post(postDto.getTitle(), postDto.getAuthor(), MultilineText.from(postDto.getContent()));
        postRepository.save(post);
        
        return PostDto.from(post);
    }
}
