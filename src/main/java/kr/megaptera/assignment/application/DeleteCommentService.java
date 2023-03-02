package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.DeleteCommentResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public DeleteCommentResponse deleteComment(Long id, Long postId) {
        Comment comment = commentRepository.delete(id);

        return new DeleteCommentResponse(comment.getId().toString(), comment.getPostId().toString(),
                comment.getAuthor(), comment.getContent());
    }
}
