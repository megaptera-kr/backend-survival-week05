package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

public class Post {
    private String id;

    private String title;

    private String author;

    private String content;

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

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
