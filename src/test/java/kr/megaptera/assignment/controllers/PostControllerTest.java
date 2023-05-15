package kr.megaptera.assignment.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.*;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Test
    @DisplayName("GET /posts")
    void list() {
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() {   
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() {
    }

    @Test
    @DisplayName("POST /posts")
    void create() {
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() {
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() {
    }
}
