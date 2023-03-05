package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public CommentDto updateCommentDto(String id, String postId, CommentDto commentDto) {
        Comment comment = commentRepository.find(CommentId.of(id));
        comment.update(commentDto.getContent());
        return new CommentDto(comment);
    }

}
