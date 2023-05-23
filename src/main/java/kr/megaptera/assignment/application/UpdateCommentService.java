package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.springframework.stereotype.*;

@Service
public class UpdateCommentService {private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto updateComment(String id, String postId,
                                    CommentDto commentDto) throws CommentNotFound {
        Comment comment = commentRepository
                .find(CommentId.of(id), PostId.of(postId));

        comment.update(commentDto.getContent());

        return new CommentDto(comment);
    }
}
