package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final PostRepository postRepository;

    public PostDto addPostDto(PostDto postDto) {
        Post post = new Post(postDto.getTitle(), postDto.getAuthor(), postDto.getContent());
        postRepository.save(post);
        return new PostDto(post);
    }

}
