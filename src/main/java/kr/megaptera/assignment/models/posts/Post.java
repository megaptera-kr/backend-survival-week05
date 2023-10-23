package kr.megaptera.assignment.models.posts;

import kr.megaptera.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.assignment.dtos.posts.UpdatePostDto;

public class Post {
    PostId id; // 게시물 아이디 값
    String title; // 게시물 제목
    String author; // 게시물 작성자
    String content; // 게시물 내용

    public Post(String title, String author, String content) {
        this.id = new PostId();
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public static Post of(CreatePostDto createPostDto) {
        return new Post(
                createPostDto.getTitle(),
                createPostDto.getAuthor(),
                createPostDto.getContent()
        );
    }

    public PostId getId() {
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

    public void update(UpdatePostDto updatePostDto) {
        this.title = updatePostDto.getTitle();
        this.content = updatePostDto.getContent();
    }
}
