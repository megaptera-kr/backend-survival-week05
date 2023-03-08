package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto delete(String id, String postId) {
        Comment comment = commentRepository.find(CommentId.of(id), PostId.of(postId));
        commentRepository.remove(comment);
        return new CommentDto(comment);
    }
}
