package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {
    @Test
    void create() {
        Post post = new Post("title", "author", MultilineText.from("content"));
        // test copy constructor
        Post copy = new Post(post, "newTitle", MultilineText.from("newContent"));
        assertThat(copy.id()).isEqualTo(post.id());
        // test PostId.generate()
        assertThat(post.id()).isNotNull();
        // test unique id
        Post secondPost = new Post("title2", "author2", MultilineText.from("content2"));
        assertThat(post.id()).isNotEqualTo(secondPost.id());
        // check content value
        assertThat(post.title()).isEqualTo("title");
        assertThat(post.author()).isEqualTo("author");
        assertThat(post.content()).isEqualTo(MultilineText.from("content"));
    }
}
