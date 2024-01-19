package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {

    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> list(String postId) {
        return this.commentRepository.findAll(PostId.from(postId))
                .stream()
                .map(CommentDto::from)
                .toList();
    }
}
