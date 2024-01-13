package kr.megaptera.assignment.converters;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.models.*;

public class CommentConverter {
    public static Comment toComment(PostId postId, CommentDetailDto dto) {
        return new Comment(
                CommentId.of(dto.getId()),
                postId,
                SingleLineText.of(dto.getAuthor()),
                MultiLineText.of(dto.getContent())
        );
    }

    public static Comment toComment(PostId postId, CommentCreateDto dto) {
        return new Comment(
                CommentId.generate(),
                postId,
                SingleLineText.of(dto.getAuthor()),
                MultiLineText.of(dto.getContent())
        );
    }

    public static CommentDetailDto toCommentDetailDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        return new CommentDetailDto(
                comment.getId().id(),
                comment.getPostId().id(),
                comment.getAuthor().text(),
                comment.getContent().toString()
        );
    }
}
