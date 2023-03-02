package kr.megaptera.assignment.dtos;


import kr.megaptera.assignment.models.Comment;

public class CreateCommentRequest {
    private String author;
    private String content;

    public CreateCommentRequest() {
    }

    public CreateCommentRequest(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Comment toComment(Long postId) {
        return new Comment(postId, author, content);
    }
}
