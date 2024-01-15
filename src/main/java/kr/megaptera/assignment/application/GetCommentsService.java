package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.CommentConverter;
import kr.megaptera.assignment.dtos.CommentDetailDto;
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

    public List<CommentDetailDto> getComments(String postId) {
        return commentRepository.findAll(PostId.of(postId)).stream()
                .map(CommentConverter::toCommentDetailDto)
                .toList();
    }
}
