package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.*;

public class PostDto {
    private String id;

    private String title;

    private String author;

    private String content;

    public PostDto(){

    }

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }


    public PostDto(Post post) {
        this(post.getId().toString(), post.getTitle(), post.getAuthor(),post.getContent());
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
