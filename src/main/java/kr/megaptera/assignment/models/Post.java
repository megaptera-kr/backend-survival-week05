package kr.megaptera.assignment.models;

public class Post {
    private PostId id;
    private String title;
    private String author;
    private MultiLineText content;

    public Post(PostId id, String title, String author, MultiLineText content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(String title, String author, MultiLineText content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(String title, MultiLineText content){
        this.title = title;
        this.content = content;
    }

    public PostId id(){
        return id;
    }

    public String title(){
        return title;
    }

    public String author(){
        return author;
    }

    public MultiLineText content(){
        return content;
    }
}
