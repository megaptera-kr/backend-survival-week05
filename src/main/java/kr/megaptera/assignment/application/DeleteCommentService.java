package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentResponseDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
  private final CommentRepository commentRepository;

  public DeleteCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public CommentResponseDto deleteComment(String id, String postId) {
    CommentEntity comment = commentRepository
      .find(id, postId);

    commentRepository.delete(comment.getId());

    return CommentResponseDto.of(comment);
  }
}
