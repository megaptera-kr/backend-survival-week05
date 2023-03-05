package kr.megaptera.assignment.entities;

public class CommentEntity {
  private String id;
  private String postId;
  private String author;
  private String content;

  public String getId() {
    return id;
  }

  public String getPostId() {
    return postId;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public CommentEntity() {
  }

  public CommentEntity(String id, String postId, String author, String content) {
    this.id = id;
    this.postId = postId;
    this.author = author;
    this.content = content;
  }
}
