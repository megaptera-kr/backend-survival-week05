package kr.megaptera.assignment.model;

import kr.megaptera.assignment.dtos.CommentDto;

public class Comment {
    private String postId;
    private String id;
    private String author;
    private String content;

    public Comment() {
    }

    public Comment(String postId, String id, String author, String content) {
        this.postId = postId;
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Comment(CommentDto dto) {
        this.postId = dto.getPostId();
        this.id = dto.getId();
        this.author = dto.getAuthor();
        this.content = dto.getContent();
    }

    public String postId() {
        return postId;
    }

    public String id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
