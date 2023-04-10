package kr.megaptera.assignment.models;

public class Comment {
    private CommentId id;
    private String author;
    private String content;
    private PostId postId;

    public Comment(CommentId id, String author, String content, PostId postId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    public Comment(PostId postId, String author, String content) {
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public PostId postId() {
        return postId;
    }
    public String content(){return content;}
    public void update(String content){
        this.content = content;
    }
    public CommentId id() {
        return id;
    }
}
