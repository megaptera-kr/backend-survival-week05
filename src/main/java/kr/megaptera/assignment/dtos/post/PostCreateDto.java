package kr.megaptera.assignment.dtos.post;

import com.github.f4b6a3.tsid.*;

public class PostCreateDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostCreateDto() {
    }

    public PostCreateDto(String title, String author, String content) {
        this.id = TsidCreator.getTsid().toString();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
