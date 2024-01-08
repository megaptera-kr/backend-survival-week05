package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto updateCommentDto(String commentId, CommentDto commentDto) {
        Comment comment = commentRepository.find(CommentId.of(commentId));
        if (comment == null) {
            throw new PostNotFound();
        }

        comment.update(MultilineText.of(commentDto.getContent()));
        return new CommentDto(comment);
    }
}
