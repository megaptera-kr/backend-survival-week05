package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public CommentDto deleteComment(String id, String postId) throws CommentNotFound {
        Comment comment = commentRepository.find(id, postId);
        commentRepository.delete(comment.getId());

        return new CommentDto(comment);
    }

}
