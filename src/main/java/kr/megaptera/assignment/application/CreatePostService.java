package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostDto postDto) {
        Post post = new Post(
                postDto.getTitle(),
                postDto.getAuthor(),
                MultilineText.of(postDto.getContent())
        );

        postRepository.save(post);


        return new PostDto(
                post.id().toString(),
                post.title(),
                post.author(),
                post.content().toString()
        );

    }

}
