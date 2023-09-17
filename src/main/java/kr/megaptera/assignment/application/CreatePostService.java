package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dto.PostCreateDto;
import kr.megaptera.assignment.dto.PostDto;
import kr.megaptera.assignment.model.MultiLineText;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.model.SingleLineText;
import kr.megaptera.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatePostService {
    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post(
                new SingleLineText(postCreateDto.getTitle()),
                new SingleLineText(postCreateDto.getAuthor()),
                new MultiLineText(postCreateDto.getContent()));
        postRepository.save(post);

        return new PostDto(post);
    }
}
