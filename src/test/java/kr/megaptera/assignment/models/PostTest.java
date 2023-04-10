package kr.megaptera.assignment.models;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {
    @Test
    @DisplayName("Creation Test")
    void creation(){
        Post post = new Post(PostId.of("1"),"title","author","content");

        assertThat(post.getTitle()).isEqualTo("title");
    }

    @Test
    @DisplayName("update Test")
    void update(){
        Post post = new Post(PostId.of("1"),"title","author","content");
        post.update("new title","new content");
        assertThat(post.getTitle()).isEqualTo("new title");
    }
}
