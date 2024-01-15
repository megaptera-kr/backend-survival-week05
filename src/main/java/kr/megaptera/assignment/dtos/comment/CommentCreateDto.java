package kr.megaptera.assignment.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentCreateDto {

    private String author;

    private String content;

    public CommentCreateDto(String author, String content) {
        this.author = author;
        this.content = content;
    }
}
