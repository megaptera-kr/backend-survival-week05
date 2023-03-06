package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {

    private final PostRepository postRepository;

    public PostDto updatePostDto(String id, PostDto postDto) {
        Post post = postRepository.find(PostId.of(id));
        post.update(postDto.getTitle(), postDto.getContent());
        return new PostDto(post);
    }

}
