package kr.megaptera.assignment.model.post;

public class Post {
    private PostId id;
    private String title;
    private String author;
    private PostContent postContent;

    public Post(PostId postId, String title, String author, PostContent postContent) {
        this.id = postId;
        this.title = title;
        this.author = author;
        this.postContent = postContent;
    }

    public Post(String title, String author, PostContent postContent) {
        this.id = PostId.generate();
        this.title = title;
        this.author = author;
        this.postContent = postContent;
    }

    public void update(String title, PostContent postContent) {
        this.title = title;
        this.postContent = postContent;
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

    public PostContent postContent() {
        return postContent;
    }
}
