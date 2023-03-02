package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.GetPostResponse;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostRepository postRepository;

    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public GetPostResponse getPost(Long id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new PostNotFoundException("Post가 존재하지 않습니다.");
        }

        return new GetPostResponse(post.getId().toString(), post.getTitle(),
                post.getAuthor(), post.getContent());
    }
}
