package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentDto {
    private String id;
    private String postId;
    private String author;
    private String content;

    public CommentDto() {
    }

    public CommentDto(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment) {
        this.id = comment.id().toString();
        this.postId = comment.postId().toString();
        this.author = comment.author();
        this.content = comment.content().toString();
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
