package kr.megaptera.assignment.controllers.dtos;

public class CommentCreateDto {
    private String postId;
    private String author;
    private String content;

    public CommentCreateDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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
