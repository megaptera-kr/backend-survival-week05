package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateRequestDto;
import kr.megaptera.assignment.dtos.CommentResponseDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {
  private final CommentRepository commentRepository;

  public CreateCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public CommentResponseDto createComment(String postId,
                                          CommentCreateRequestDto commentCreateDto) {
    CommentEntity comment = new CommentEntity(
      postId,
      commentCreateDto.getAuthor(),
      commentCreateDto.getContent()
    );

    commentRepository.save(comment);

    return CommentResponseDto.of(comment);
  }
}
