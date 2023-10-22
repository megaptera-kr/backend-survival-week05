package kr.megaptera.assignment.dtos.posts;

public class CreatePostDto {
    String title; // 게시물 제목
    String author; // 게시물 작성자
    String content; // 게시물 내용

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
