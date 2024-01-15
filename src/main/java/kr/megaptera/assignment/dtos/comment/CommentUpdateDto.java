package kr.megaptera.assignment.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentUpdateDto {

    private String content;

    public CommentUpdateDto(String content) {
        this.content = content;
    }
}
