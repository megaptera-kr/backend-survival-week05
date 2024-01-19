package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;

    public UpdateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto update(String id, String postId, CommentDto commentDto) {
        Comment found = commentRepository.findAll(PostId.from(postId)).stream()
                .filter(comment -> comment.id().equals(CommentId.from(id)))
                .findFirst()
                .orElseThrow(() -> new CommentNotFoundException("Invalid comment id"));
        Comment updated = new Comment(found, MultilineText.from(commentDto.getContent()));
        commentRepository.save(PostId.from(postId), updated);

        return CommentDto.from(updated);
    }
}
