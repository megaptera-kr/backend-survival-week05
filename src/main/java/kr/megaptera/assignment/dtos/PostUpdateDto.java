package kr.megaptera.assignment.dtos;

public class PostUpdateDto {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String title;
    private String content;

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }
}
