package kr.megaptera.assignment.models.comments;

import kr.megaptera.assignment.dtos.comments.CreateCommentDto;
import kr.megaptera.assignment.dtos.comments.UpdateCommentDto;

public class Comment {
    CommentId id;
    String author;
    String content;

    public Comment(String author, String content) {
        this.id = new CommentId();
        this.author = author;
        this.content = content;
    }

    public static Comment of(CreateCommentDto createCommentDto) {
        return new Comment(createCommentDto.getAuthor(), createCommentDto.getContent());
    }

    public CommentId getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void update(UpdateCommentDto updateCommentDto) {
        this.author = updateCommentDto.getAuthor();
        this.content = updateCommentDto.getContent();
    }
}
