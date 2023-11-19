package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Post;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String content;

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(Post post) {
        this.id = post.id().toString();
        this.title = post.title().toString();
        this.author = post.author().toString();
        this.content = post.content().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
