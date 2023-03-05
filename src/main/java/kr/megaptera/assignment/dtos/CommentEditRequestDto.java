package kr.megaptera.assignment.dtos;

public class CommentEditRequestDto {
  private String content;

  public String getContent() {
    return content;
  }

  public CommentEditRequestDto() {
  }

  public CommentEditRequestDto(String content) {
    this.content = content;
  }
}
