package kr.megaptera.assignment.application;


import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.springframework.stereotype.*;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto createCommentDto(String postId, CommentCreateDto commentCreateDto) {
        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent());

        commentRepository.save(comment);

        return new CommentDto(comment);
    }
}
