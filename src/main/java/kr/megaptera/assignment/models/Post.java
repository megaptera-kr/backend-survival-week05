package kr.megaptera.assignment.models;

import kr.megaptera.assignment.entities.PostEntity;
import lombok.Data;

@Data
public class Post {
    private PostId id;
    private String title;
    private String author;
    private MultilineText content;

    public Post(PostEntity entity) {
        id = new PostId(entity.getId());
        title = entity.getTitle();
        author = entity.getAuthor();
        content = new MultilineText(entity.getContent());
    }

    public Post(PostId id, String title, String author, MultilineText content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.id = new PostId();
        this.title = title;
        this.author = author;
        this.content = new MultilineText(content);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content.appendText(content);
    }
}
