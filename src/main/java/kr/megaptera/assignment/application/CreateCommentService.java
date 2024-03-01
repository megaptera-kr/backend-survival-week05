package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    @Autowired
    private CommentRepository commentRepository;
    public CommentDto createComment(CommentDto comment) {
        commentRepository.createComment(comment);
        return comment;
    }
}
