package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    @Autowired
    CommentRepository commentRepository;
    public CommentDto updateComment(CommentDto comment) {
        CommentDto getDbComment = commentRepository.getCommentByCommentId(comment);
        commentRepository.updateComment(comment);
        return getDbComment;
    }
}
