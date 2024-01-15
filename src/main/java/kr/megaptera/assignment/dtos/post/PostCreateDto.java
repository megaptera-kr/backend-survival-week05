package kr.megaptera.assignment.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostCreateDto {
    
    private String title;

    private String author;

    private String content;

    public PostCreateDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
