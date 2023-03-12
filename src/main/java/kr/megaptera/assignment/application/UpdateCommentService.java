package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentResponseDto;
import kr.megaptera.assignment.dtos.CommentUpdateRequestDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {
  private final CommentRepository commentRepository;

  public UpdateCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public CommentResponseDto updateComment(String id, String postId,
                                          CommentUpdateRequestDto commentUpdatedDto) {
    CommentEntity comment = commentRepository
      .find(id, postId);

    comment.update(commentUpdatedDto.getContent());

    return CommentResponseDto.of(comment);
  }
}
