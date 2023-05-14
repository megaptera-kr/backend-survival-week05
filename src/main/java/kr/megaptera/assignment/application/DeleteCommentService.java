package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public CommentDto deleteComment(String id, String postId) {
        return new CommentDto(commentRepository.delete(CommentId.of(id), PostId.of(postId)));
    }
}
