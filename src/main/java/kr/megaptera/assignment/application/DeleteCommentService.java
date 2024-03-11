package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    @Autowired
    CommentRepository commentRepository;
    public CommentDto deleteComment(CommentDto dto) {
        dto = commentRepository.getCommentByCommentId(dto);
        commentRepository.deleteComment(dto);
        return dto;
    }
}
