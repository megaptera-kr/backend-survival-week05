package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public GetCommentsService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<CommentDto> list(String postId) {
        Optional<Post> tPost = postRepository.find(PostId.of(postId));
        if (tPost.isEmpty()) {
            throw new PostNotFound();
        }

        return commentRepository.findAll(PostId.of(postId))
                .stream()
                .map(CommentDto::new)
                .toList();
    }
}
