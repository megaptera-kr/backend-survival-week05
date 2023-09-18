package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentCreateDto {
    private String author;
    private String content;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public CommentCreateDto(Comment comment) {
        this(comment.author(), comment.content().toString());
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
