package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.CommentConverter;
import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDetailDto updateComment(String id, String postId, CommentUpdateDto dto) {
        Comment comment = commentRepository.find(
                CommentId.of(id),
                PostId.of(postId));
        comment.update(MultiLineText.of(dto.getContent()));
        commentRepository.save(comment);
        return CommentConverter.toCommentDetailDto(commentRepository.find(comment.getId(), comment.getPostId()));
    }
}
