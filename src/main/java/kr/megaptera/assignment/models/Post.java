package kr.megaptera.assignment.models;

public class Post {

    private final PostId id;
    private final String title;
    private final String author;
    private final MultilineText content;

    public Post(String title, String author, String content) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.content = MultilineText.from(content);
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
