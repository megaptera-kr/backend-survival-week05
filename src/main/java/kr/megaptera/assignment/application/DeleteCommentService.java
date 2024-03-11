package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public DeleteCommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDto delete(String postId, String commentId) {
        if (postRepository.find(PostId.of(postId)).isEmpty()) {
            throw new PostNotFound();
        }

        Optional<Comment> comment = commentRepository.find(CommentId.of(commentId), PostId.of(postId));
        if (comment.isEmpty()) {
            throw new CommentNotFound();
        }

        commentRepository.delete(CommentId.of(commentId));

        return new CommentDto(comment.get());
    }
}
