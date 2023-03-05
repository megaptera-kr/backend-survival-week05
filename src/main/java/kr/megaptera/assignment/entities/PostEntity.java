package kr.megaptera.assignment.entities;

public class PostEntity implements Cloneable {
  private String id;
  private String title;
  private String author;
  private String content;

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

  public PostEntity() {
  }

  public PostEntity(String id, String title, String author, String content) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.content = content;
  }
}
