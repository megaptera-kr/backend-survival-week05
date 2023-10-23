package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentDto;
import kr.megaptera.assignment.exceptions.comments.CommentNotFound;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto delete(String postId, String commentId) {
        Comment commentToBeRemoved = this.commentRepository.find(postId, commentId);

        if (commentToBeRemoved == null) {
            throw new CommentNotFound();
        }

        this.commentRepository.remove(postId, commentId);

        return CommentDto.of(commentToBeRemoved);
    }
}
