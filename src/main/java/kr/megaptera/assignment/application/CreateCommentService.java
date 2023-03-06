package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto createComment(CommentDto commentDto, String postId) {

        Comment comment = new Comment(
                PostId.of(postId),
                commentDto.getAuthor(),
                MultilineText.of(commentDto.getContent())
        );

        commentRepository.save(comment);

        return new CommentDto(
                comment.id().toString(),
                comment.author(),
                comment.content().toString()
        );

    }

}
