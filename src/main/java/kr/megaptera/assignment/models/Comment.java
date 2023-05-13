package kr.megaptera.assignment.models;

import java.util.Objects;

public class Comment {
    private CommentId id;

    private PostId postId;
    private String author;
    private CommentContent content;

    public Comment(CommentId id, String author, CommentContent content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, String author, CommentContent content) {
        this.id = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentId Id() {
        return id;
    }

    public String Author() {
        return author;
    }

    public CommentContent Content() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(author, comment.author) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, content);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content=" + content +
                '}';
    }

    public PostId postId() {
        return postId;
    }

    public void update(CommentContent text) {
        this.content = text;
    }
}
