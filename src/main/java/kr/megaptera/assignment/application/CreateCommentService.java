package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CreateCommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto create(String postId, CreateCommentDto createCommentDto) {
        Comment comment = new Comment(PostId.of(postId), createCommentDto.getAuthor(), createCommentDto.getContent());
        commentRepository.save(comment);
        return new CommentDto(comment);
    }
}
