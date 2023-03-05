package kr.megaptera.assignment.application;


import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto deleteComment(String id) {

        Comment comment = commentRepository.find(CommentId.of(id));
        commentRepository.delete(CommentId.of(id));

        return new CommentDto(
                comment.id().toString(),
                comment.author(),
                comment.content().toString()
        );

    }

}
