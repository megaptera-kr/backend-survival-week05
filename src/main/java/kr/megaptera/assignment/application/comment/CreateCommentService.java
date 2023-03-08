package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto create(String postId,
                             CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent()
        );
        commentRepository.save(comment);
        return new CommentDto(comment);
    }
}
