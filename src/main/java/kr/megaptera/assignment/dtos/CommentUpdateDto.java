package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentUpdateDto {
    private String content;

    public CommentUpdateDto() {
    }

    public CommentUpdateDto(String content) {
        this.content = content;
    }

    public CommentUpdateDto(Comment comment) {
        this(comment.content().toString());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
