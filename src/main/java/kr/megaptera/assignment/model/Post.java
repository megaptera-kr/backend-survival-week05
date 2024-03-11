package kr.megaptera.assignment.model;

import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.assignment.dtos.PostDto;

public class Post {
    private String id;
    private String title;
    private String author;
    private String content;

    public Post(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, String content) {
        this.id = TsidCreator.getTsid().toString();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(PostDto dto) {
        this(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getContent());
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }
}
