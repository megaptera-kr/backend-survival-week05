package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto updateComment(String id, String postId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.find(CommentId.of(id),PostId.of(id));

        comment.update(commentUpdateDto.getContent());

        return new CommentDto(comment);
    }
}
