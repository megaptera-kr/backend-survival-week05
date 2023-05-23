package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Post;

public class PostCreateDto {
    public PostCreateDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
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

    private String title;

    private String author;

    private String content;

    public Post toPostModel (PostCreateDto postCreateDto){
        return new Post(this.title,this.author,this.content);
    }
}
