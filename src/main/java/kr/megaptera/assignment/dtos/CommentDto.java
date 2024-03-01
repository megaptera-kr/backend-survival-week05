package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.model.Comment;

public class CommentDto {
    private String postId;
    private String id;
    private String author;
    private String content;

    public CommentDto(String postId, String id, String author, String content) {
        this.postId = postId;
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentDto() {
    }

    public CommentDto(Comment comment) {
        this.postId = comment.postId();
        this.id = comment.id();
        this.content = comment.content();
        this.author = comment.author();
    }

    public CommentDto(String postId) {
        this.postId = postId;
    }

    public CommentDto(String postId, String id) {
        this.postId = postId;
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
