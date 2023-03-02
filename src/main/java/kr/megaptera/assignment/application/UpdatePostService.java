package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.UpdatePostRequest;
import kr.megaptera.assignment.dtos.UpdatePostResponse;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public UpdatePostResponse updatePost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new PostNotFoundException("Post가 존재하지 않습니다.");
        }

        post.changeTitle(request.getTitle());
        post.changeContent(request.getContent());

        return new UpdatePostResponse(post.getId().toString(), request.getTitle(), post.getAuthor(),
                request.getContent());
    }
}
