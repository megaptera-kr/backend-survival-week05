package kr.megaptera.assignment.dtos.posts;

import kr.megaptera.assignment.models.posts.Post;

public class PostDto {
    String id; // 게시물 아이디 값
    String title; // 게시물 제목
    String author; // 게시물 작성자
    String content; // 게시물 내용

    public PostDto(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public static PostDto of(Post post) {
        return new PostDto(post.getId().toString(), post.getTitle(), post.getAuthor(), post.getContent());
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
