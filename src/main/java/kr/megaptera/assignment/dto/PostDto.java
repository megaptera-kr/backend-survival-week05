package kr.megaptera.assignment.dto;

import kr.megaptera.assignment.model.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class PostDto {
    private String id;
    private String title;
    private String author;
    private String comment;

    public PostDto(Post post) {
        this.id = post.id().toString();
        this.title = post.title().toString();
        this.author = post.author().toString();
        this.comment = post.comment().toString();
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
