package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.UpdateCommentRequest;
import kr.megaptera.assignment.dtos.UpdateCommentResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public UpdateCommentResponse updateComment(Long id, Long postId,
            UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(id);

        comment.changeContent(request.getContent());

        return new UpdateCommentResponse(comment.getId().toString(), comment.getPostId().toString(),
                comment.getAuthor(), comment.getContent());
    }
}
