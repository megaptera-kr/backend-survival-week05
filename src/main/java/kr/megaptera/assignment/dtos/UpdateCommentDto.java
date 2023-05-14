package kr.megaptera.assignment.dtos;

public class UpdateCommentDto {
    private String content;

    public UpdateCommentDto() {
    }

    public UpdateCommentDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
