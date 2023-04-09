package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public DeleteCommentService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentReadDto execute(String commentId) throws CommentNotFoundException {
        var entity = commentRepository.find(commentId);
        if (entity == null) {
            throw new CommentNotFoundException();
        }

        commentRepository.remove(entity);

        var model = new Comment(entity);
        var dto = new CommentReadDto(model);

        return dto;
    }
}
