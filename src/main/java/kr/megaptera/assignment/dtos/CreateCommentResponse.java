package kr.megaptera.assignment.dtos;

public class CreateCommentResponse {
    private String id;
    private String postId;
    private String author;
    private String content;

    public CreateCommentResponse() {
    }

    public CreateCommentResponse(String id, String postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public String getPostId() {
        return postId;
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
