package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;

public class GetCommentsService {
    public static CommentDto getCommentDtos(String postId) {
        Comment comment = CommentRepository.findAll();

        return new CommentDto(comment);
    }
}
