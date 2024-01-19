package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
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

    public CommentDto create(String postId, CommentDto commentDto) {
        Comment comment = new Comment(commentDto.getAuthor(), MultilineText.from(commentDto.getContent()));
        commentRepository.save(PostId.from(postId), comment);
        
        return CommentDto.from(comment);
    }
}
