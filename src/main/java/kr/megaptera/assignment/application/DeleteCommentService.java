package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto deleteComment(String id, String postId) {
        Comment comment = commentRepository.find(CommentId.of(id), PostId.of(postId));
        commentRepository.delete(comment.id());
        return new CommentDto(comment);
    }
}
