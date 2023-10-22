package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentDto;
import kr.megaptera.assignment.dtos.comments.UpdateCommentDto;
import kr.megaptera.assignment.exceptions.comments.CommentNotFound;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto update(String postId, String commentId, UpdateCommentDto updateCommentDto) {
        Comment commentToBeUpdated = this.commentRepository.find(postId, commentId);

        if (commentToBeUpdated == null) {
            throw new CommentNotFound();
        }

        commentToBeUpdated.update(updateCommentDto);

        return CommentDto.of(commentToBeUpdated);
    }
}
