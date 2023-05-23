package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {
    @Test
    void creation() {
        Post post = new Post(
                new PostTitle("제목"),
                "작성자",
                new MultiLineText("내용")
        );

        assertThat(post.id()).isNotNull();
        assertThat(post.title()).isEqualTo(PostTitle.of("제목"));
        assertThat(post.author()).isEqualTo("작성자");
        assertThat(post.content().toString()).isEqualTo("내용");
    }

    @Test
    void update() {
        Post post = new Post(
                new PostId("1"),
                new PostTitle("제목"),
                "작성자",
                new MultiLineText("내용")
        );

        post.update(new PostTitle("변경된 제목"), new MultiLineText("변경된 내용"));

        assertThat(post.title()).isEqualTo(PostTitle.of("변경된 제목"));
        assertThat(post.author()).isEqualTo("작성자");
        assertThat(post.content().toString()).isEqualTo("변경된 내용");
    }
}
