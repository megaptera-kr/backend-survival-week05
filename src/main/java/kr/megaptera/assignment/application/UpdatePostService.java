package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostRepository postRepository;

    public PostDto updatePost(String id, PostDto postDto) throws PostNotFound {
        Post post = postRepository.find(id);
        post.update(postDto.getTitle(), postDto.getContent());

        return new PostDto(post);
    }
}
