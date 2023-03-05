package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public CommentDto removeCommentDto(String id) {
        Comment comment = commentRepository.find(CommentId.of(id));
        commentRepository.delete(CommentId.of(id));
        return new CommentDto(comment);
    }

}
