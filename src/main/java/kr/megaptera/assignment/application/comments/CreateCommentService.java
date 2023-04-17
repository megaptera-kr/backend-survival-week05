package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CreateCommentService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentReadDto execute(String postId, CommentCreateDto commentCreateDto) {
        var model = new Comment(postId, commentCreateDto);
        var entity = new CommentEntity(model);

        commentRepository.add(entity);

        var commentReadDto = new CommentReadDto(model);
        return commentReadDto;
    }
}
