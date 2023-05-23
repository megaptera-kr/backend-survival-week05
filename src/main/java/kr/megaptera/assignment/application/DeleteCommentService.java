package kr.megaptera.assignment.application;

import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteComment(String commentId, String postId) {
        commentRepository.delete(postId,commentId);
    }
}
