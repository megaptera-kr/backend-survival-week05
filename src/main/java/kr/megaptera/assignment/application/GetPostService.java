package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetPostService {

    private final PostRepository postRepository;

    public PostDto getPost(String id) {
        Post post = postRepository.find(id);
        if (post == null)
            throw new PostNotFound();
        return new PostDto(post);
    }
}
