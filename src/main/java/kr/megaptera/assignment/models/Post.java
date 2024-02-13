package kr.megaptera.assignment.models;

public class Post {

    private final PostId id;
    private final String title;
    private final String author;
    private final MultilineText content;

    public Post(String title, String author, MultilineText content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Post(Post post, String title, MultilineText content) {
        this.id = post.id();
        this.title = title;
        this.author = post.author();
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }
    
}
