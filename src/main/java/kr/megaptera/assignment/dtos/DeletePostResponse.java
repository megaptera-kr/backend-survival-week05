package kr.megaptera.assignment.dtos;

public class DeletePostResponse {
    private String id;
    private String title;
    private String author;
    private String content;

    public DeletePostResponse(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
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
