package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CommentDto createComment(String postId, CommentDto commentDto) {
        Comment comment = Comment.builder()
                .postId(postId)
                .author(commentDto.getAuthor())
                .content(commentDto.getContent())
                .build();
        commentRepository.save(comment);

        return new CommentDto(comment);
    }
}
