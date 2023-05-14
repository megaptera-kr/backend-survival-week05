package kr.megaptera.assignment.models;

import kr.megaptera.assignment.dtos.UpdateCommentDto;

public class Comment {
    private CommentId commentId;
    private PostId postId;
    private String author;
    private String content;

    public Comment(CommentId commentId, PostId postId, String author, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public Comment(PostId postId, String author, String content) {
        commentId = CommentId.generate();
        this.postId = postId;
        this.author = author;
        this.content = content;
    }

    public CommentId commentId() {
        return commentId;
    }

    public PostId postId() {
        return postId;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public void update(UpdateCommentDto updateCommentDto) {
        this.content = updateCommentDto.getContent();
    }
}
