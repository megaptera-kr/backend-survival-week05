package kr.megaptera.assignment.models;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {

    @Test
    @DisplayName("Comment Creation")
    void creation(){
        Comment comment = new Comment(CommentId.of("1"),"author","content",PostId.of("1"));
        assertThat(comment.content()).isEqualTo("content");
    }

    @Test
    @DisplayName("Update Comment")
    void update(){
        Comment comment = new Comment(CommentId.of("1"),"author","content",PostId.of("1"));
        comment.update("new content");
        assertThat(comment.content()).isEqualTo("new content");
    }
}
