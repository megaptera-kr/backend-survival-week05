package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.DeletePostResponse;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public DeletePostResponse deletePost(Long id) {
        Post post = postRepository.delete(id);

        return new DeletePostResponse(post.getId().toString(), post.getTitle(), post.getAuthor(),
                post.getContent());
    }
}
