package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.CommentConverter;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDetailDto;
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

    public CommentDetailDto createComment(String postId, CommentCreateDto dto) {
        Comment comment = CommentConverter.toComment(PostId.of(postId), dto);
        commentRepository.save(comment);
        return CommentConverter.toCommentDetailDto(commentRepository.find(comment.getId(), comment.getPostId()));
    }
}
