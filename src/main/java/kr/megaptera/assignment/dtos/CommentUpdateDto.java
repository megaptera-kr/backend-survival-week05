package kr.megaptera.assignment.dtos;

public class CommentUpdateDto {

    private final String content;

    public CommentUpdateDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
