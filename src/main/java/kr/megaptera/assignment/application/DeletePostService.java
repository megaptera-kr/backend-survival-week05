package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostRepository postRepository;

    public PostDto deletePost(String id) throws PostNotFound {
        Post post = postRepository.find(id);
        postRepository.delete(id);

        return new PostDto(post);
    }

}
