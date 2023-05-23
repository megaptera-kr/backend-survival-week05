package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto create(PostCreateDto postCreateDto) {
        Post post = new Post(
                PostTitle.of(postCreateDto.getTitle()),
                postCreateDto.getAuthor(),
                MultiLineText.of(postCreateDto.getContent())
        );
        postRepository.save(post);
        return new PostDto(post);
    }
}
