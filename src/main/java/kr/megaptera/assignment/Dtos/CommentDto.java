package kr.megaptera.assignment.Dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentDto {

    private final String id;
    private final String author;
    private final String content;

    private CommentDto(String id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public static CommentDto from(Comment comment) {
        return new CommentDto(comment.id().toString(), comment.author(), comment.content().toString());
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
