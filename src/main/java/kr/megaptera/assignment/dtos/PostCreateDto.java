package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Post;

public class PostCreateDto {
    private String title;
    private String author;
    private String content;

    public PostCreateDto() {

    }

    public PostCreateDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostCreateDto(Post post) {
        this(post.title(), post.author(), post.content().toString());
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
