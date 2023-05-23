package kr.megaptera.assignment.dtos;

public class CreateCommentDto {
    private String author;
    private String content;

    public CreateCommentDto() {
    }

    public CreateCommentDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
