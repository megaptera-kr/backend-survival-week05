package kr.megaptera.assignment.dtos.comments;

import kr.megaptera.assignment.models.comments.Comment;

public class CommentDto {
    String id;
    String author;
    String content;

    public CommentDto(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public static CommentDto of(Comment comment) {
        return new CommentDto(comment.getId().toString(), comment.getAuthor(), comment.getContent());
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
