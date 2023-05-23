package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdatePostService {

    private final PostRepository postRepository;

    public PostDto updatePost(String id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.find(id);

        if (post == null)
            throw new PostNotFound();

        post.updatePost(postUpdateDto);
        postRepository.save(post);
        return new PostDto(post);
    }
}
