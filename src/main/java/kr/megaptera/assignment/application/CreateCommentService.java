package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CreateCommentResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CreateCommentResponse createComment(Comment comment) {
        Comment result = commentRepository.save(comment);

        return new CreateCommentResponse(result.getId().toString(), result.getPostId().toString(),
                result.getAuthor(), result.getContent());
    }
}
