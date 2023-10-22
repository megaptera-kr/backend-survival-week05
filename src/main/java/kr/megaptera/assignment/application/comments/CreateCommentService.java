package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentDto;
import kr.megaptera.assignment.dtos.comments.CreateCommentDto;
import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto create(String postId, CreateCommentDto createCommentDto) {
        Comment newComment = Comment.of(createCommentDto);

        this.commentRepository.save(postId, newComment);

        return CommentDto.of(newComment);
    }
}
