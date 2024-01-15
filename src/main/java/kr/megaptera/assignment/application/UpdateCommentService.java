package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public CommentDto updateComment(String id, String postId, CommentDto commentDto) throws CommentNotFound {
        Comment comment = commentRepository.find(id, postId);
        comment.update(commentDto.getContent());
        commentRepository.save(comment);

        return new CommentDto(comment);
    }
}
