package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Post;

public class PostUpdateDto {
    private String title;
    private String content;

    public PostUpdateDto() {
    }

    public PostUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostUpdateDto(Post post) {
        this(post.title(), post.content().toString());
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
