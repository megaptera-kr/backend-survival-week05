package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentContent;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto update(String id, CommentDto dto) {
        Comment comment = commentRepository.findComment(CommentId.of(id));
        comment.update(CommentContent.of(dto.getContent()));
        return new CommentDto(comment);
    }
}
