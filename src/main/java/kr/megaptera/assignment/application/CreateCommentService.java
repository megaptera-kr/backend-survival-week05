package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto create(String id, CommentDto dto) {
        Comment comment = new Comment(
                PostId.of(id),
                dto.getAuthor(),
                dto.getContent()
        );

        commentRepository.save(comment);

        return new CommentDto(comment);
    }
}
