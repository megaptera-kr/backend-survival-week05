package kr.megaptera.assignment.models;

public class Post {
    private PostId id;
    private SingleLineText title;
    private SingleLineText author;
    private MultiLineText content;

    public Post(PostId id, SingleLineText title, SingleLineText author, MultiLineText content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostId getId() {
        return id;
    }

    public SingleLineText getTitle() {
        return title;
    }

    public SingleLineText getAuthor() {
        return author;
    }

    public MultiLineText getContent() {
        return content;
    }

    public static Post createNew(SingleLineText title, SingleLineText author, MultiLineText content) {
        PostId id = PostId.generate();
        return new Post(id, title, author, content);
    }

    public static Post of(PostId id, SingleLineText title, SingleLineText author, MultiLineText content) {
        return new Post(id, title, author, content);
    }

    public void updatePost(SingleLineText title, MultiLineText content) {
        this.title = title;
        this.content = content;
    }
}
