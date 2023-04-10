package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto updateComment(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.find(CommentId.of(id), PostId.of(postId));
        comment.update(commentUpdateDto.getContent());
        return new CommentDto(comment);
    }
}
