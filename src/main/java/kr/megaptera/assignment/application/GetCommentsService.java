package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getComments(String postId) {
        List<Comment> comments = commentRepository.getComments(PostId.of(postId));
        return comments.stream().map(comment -> new CommentDto(comment))
                .toList();
    }
}
