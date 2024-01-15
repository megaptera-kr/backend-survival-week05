package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.domain.Post;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PostDto {

    private String id;

    private String author;

    private String content;

    private String title;

    public PostDto(Post post) {
        this.id = post.getId();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.title = post.getTitle();

    }

}
