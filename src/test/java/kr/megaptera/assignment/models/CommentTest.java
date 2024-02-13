package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {
    @Test
    void create() {
        Comment comment = new Comment("author", MultilineText.from("content"));
        // test copy constructor
        Comment copy = new Comment(comment, MultilineText.from("newContent"));
        assertThat(copy.id()).isEqualTo(comment.id());
        // test PostId.generate()
        assertThat(comment.id()).isNotNull();
        // test unique id
        Comment comment2 = new Comment("author2", MultilineText.from("content2"));
        assertThat(comment.id()).isNotEqualTo(comment2.id());
        // check content value
        assertThat(comment.author()).isEqualTo("author");
        assertThat(comment.content()).isEqualTo(MultilineText.from("content"));
    }
}
