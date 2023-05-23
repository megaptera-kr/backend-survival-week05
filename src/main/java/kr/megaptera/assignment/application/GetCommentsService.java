package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public GetCommentsService (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<CommentDto> findCommentList(String postId) {
        return commentRepository.findByPostId(postId).stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
    }
}
