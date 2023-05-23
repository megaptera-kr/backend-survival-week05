package kr.megaptera.assignment.dtos;

public class UpdatePostDto {
    private String title;
    private String content;

    public UpdatePostDto() {
    }

    public UpdatePostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
