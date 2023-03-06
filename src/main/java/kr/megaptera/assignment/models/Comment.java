package kr.megaptera.assignment.models;

public class Comment {

    private Long id;
    private Long postId;
    private String author;
    private String content;

    public Comment(Long postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(Long id, Long postId, String author, String content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public void generateId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
