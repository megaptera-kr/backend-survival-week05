package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {
    @Test
    void creation() {
        PostId postId = new PostId("1");

        Comment comment = new Comment(
                postId,
                "작성자",
                new MultiLineText("댓글 내용")
        );

        assertThat(comment.id()).isNotNull();
        assertThat(comment.postId()).isEqualTo(postId);
        assertThat(comment.author()).isEqualTo("작성자");
        assertThat(comment.content()).isEqualTo(MultiLineText.of("댓글 내용"));
    }

    @Test
    void update() {
        PostId postId = new PostId("1");

        Comment comment = new Comment(
                postId,
                "작성자",
                new MultiLineText("댓글 내용")
        );

        comment.update(new MultiLineText("변경된 댓글"));

        assertThat(comment.content()).isEqualTo(MultiLineText.of("변경된 댓글"));
    }
}
