package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.CommentConverter;
import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDetailDto deleteComment(String postId, String id) {
        Comment comment = commentRepository.delete(PostId.of(postId), CommentId.of(id));
        return CommentConverter.toCommentDetailDto(comment);
    }
}
