package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
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
        Optional<Post> post = postRepository.findById(PostId.from(postId));
        if (post.isEmpty()) {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }

        return this.commentRepository.findAll(PostId.from(postId))
                .stream()
                .map(CommentDto::from)
                .toList();
    }
}
