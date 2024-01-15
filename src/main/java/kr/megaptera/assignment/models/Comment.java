package kr.megaptera.assignment.models;

public class Comment {
    private CommentId id;
    private PostId postId;
    private SingleLineText author;
    private MultiLineText content;

    public Comment(CommentId id, PostId postId, SingleLineText author, MultiLineText content) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment() {
    }

    public CommentId getId() {
        return id;
    }

    public PostId getPostId() {
        return postId;
    }

    public SingleLineText getAuthor() {
        return author;
    }

    public MultiLineText getContent() {
        return content;
    }

    public void update(MultiLineText content) {
        this.content = content;
    }

    public static Comment createNew(PostId postId, SingleLineText author, MultiLineText content) {
        return new Comment(
                CommentId.generate(),
                postId,
                author,
                content);
    }

    public static Comment of(CommentId commentId, PostId postId, SingleLineText author, MultiLineText content) {
        return new Comment(
                commentId,
                postId,
                author,
                content);
    }
}
