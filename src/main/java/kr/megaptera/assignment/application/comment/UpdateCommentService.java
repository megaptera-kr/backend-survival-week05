package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto update(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository
                .find(CommentId.of(id), PostId.of(postId));
        
        comment.update(commentUpdateDto);
        return new CommentDto(comment);
    }
}
