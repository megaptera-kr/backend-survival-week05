package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePostService {

    private final PostRepository postRepository;

    public PostDto deletePost(String id) {
        Post post = postRepository.find(id);
        if (post == null)
            throw new PostNotFound();
        postRepository.delete(id);
        return new PostDto(post);
    }
}
