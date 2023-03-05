package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
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


    public CommentDto updateComment(String id, CommentDto commentDto) {
        Comment comment = commentRepository.find(CommentId.of(id));
        comment.update(
                MultilineText.of(commentDto.getContent())
        );

        Comment newComment = commentRepository.find(CommentId.of(id));

        return new CommentDto(
                newComment.id().toString(),
                newComment.author(),
                newComment.content().toString()
        );
    }


}
