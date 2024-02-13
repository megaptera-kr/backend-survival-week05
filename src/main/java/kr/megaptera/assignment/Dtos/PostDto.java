package kr.megaptera.assignment.Dtos;

import kr.megaptera.assignment.models.Post;

public class PostDto {

    private final String id;
    private final String title;
    private final String author;
    private final String content;

    private PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    static public PostDto from(Post post) {
        return new PostDto(post.id().toString(), post.title(), post.author(), post.content().toString());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
