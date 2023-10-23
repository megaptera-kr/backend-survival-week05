package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> findAll(String postId) {
        return this.commentRepository.findAll(postId).stream().map(CommentDto::of).toList();
    }
}
