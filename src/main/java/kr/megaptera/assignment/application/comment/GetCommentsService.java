package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GetCommentsService {

    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getCommentDtos(String postId) {
        List<Comment> comments = commentRepository.findAll(postId);
        return comments.stream().map(CommentDto::new).toList();
    }
}
