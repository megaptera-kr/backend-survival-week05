package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public CommentDto deletePost (String commentId, String postId) {
        Comment comment = commentRepository.find(CommentId.of(commentId), PostId.of(postId));
        commentRepository.delete(CommentId.of(commentId));
        return new CommentDto(comment);
    }
}
