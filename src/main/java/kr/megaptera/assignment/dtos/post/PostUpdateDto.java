package kr.megaptera.assignment.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostUpdateDto {

    private String title;

    private String content;

    public PostUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
