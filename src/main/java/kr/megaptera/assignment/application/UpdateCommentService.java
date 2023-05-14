package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public void updateComment(String postId, String commentId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findComment(postId, commentId);
        comment.update(commentUpdateDto);
    }
}
