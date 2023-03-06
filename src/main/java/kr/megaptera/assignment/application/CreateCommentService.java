package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CommentDto addCommentDto(String postId, CommentDto commentDto) {
        commentDto.setPostId(postId);
        Comment comment = new Comment(PostId.of(commentDto.getPostId()), commentDto.getAuthor(), commentDto.getContent());
        commentRepository.save(comment);
        return new CommentDto(comment);
    }

}
