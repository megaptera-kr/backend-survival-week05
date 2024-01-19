package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {

    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto delete(String id, String postId) {
        Comment found = commentRepository.findAll(PostId.from(postId)).stream()
                .filter(comment -> comment.id().equals(CommentId.from(id)))
                .findFirst()
                .orElseThrow(() -> new CommentNotFoundException("Invalid comment id"));
        commentRepository.delete(PostId.from(postId), CommentId.from(id));

        return CommentDto.from(found);
    }
}
