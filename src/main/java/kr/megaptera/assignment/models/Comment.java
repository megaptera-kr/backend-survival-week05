package kr.megaptera.assignment.models;

public class Comment {

    private final CommentId id;
    private final String author;
    private final MultilineText content;

    public Comment(String author, MultilineText content) {
        this.id = CommentId.generate();
        this.author = author;
        this.content = content;
    }

    public Comment(Comment comment, MultilineText content) {
        this.id = comment.id();
        this.author = comment.author();
        this.content = content;
    }

    public CommentId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }
}
