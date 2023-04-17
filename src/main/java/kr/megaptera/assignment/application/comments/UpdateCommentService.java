package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public UpdateCommentService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentReadDto execute(String commentId, String postId, CommentUpdateDto commentUpdateDto) throws CommentNotFoundException {
        var oldEntity = commentRepository.find(commentId);
        if(oldEntity == null){
            throw new CommentNotFoundException();
        }

        var model = new Comment(oldEntity);

        model.update(commentUpdateDto);

        var newEntity = new CommentEntity(model);

        commentRepository.update(newEntity);

        var commentReadDto = new CommentReadDto(model);

        return commentReadDto;
    }
}
