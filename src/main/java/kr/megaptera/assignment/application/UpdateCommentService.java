package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto updateComment(String id, String postId, String content){
        Comment comment = commentRepository.find(CommentId.of(id));
        if (comment == null) {
            throw new CommentNotFound();
        }

        comment.update(content);
        return new CommentDto(comment);
    }
}
