package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentCreateDto {
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

    private String author;
    private String content;

    public Comment toCommentModel (CommentCreateDto commentCreateDto, String postId) {
        return new Comment(commentCreateDto.author, commentCreateDto.content,postId);
    }
}
