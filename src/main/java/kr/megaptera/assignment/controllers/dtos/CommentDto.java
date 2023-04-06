package kr.megaptera.assignment.controllers.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentDto {
    private String id;
    private String author;
    private String content;
    private String postId;

    public CommentDto(String id, String author, String content, String postId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    public CommentDto(CommentCreateDto commentCreateDto){


    }

    public CommentDto(Comment comment){
        this(comment.id().toString(), comment.author(), comment.content(), comment.postId().toString());
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
