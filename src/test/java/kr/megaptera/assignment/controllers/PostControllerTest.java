package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.post.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        given(getPostsService.getPostDtos()).willReturn(List.of(
                new PostDto("Test_1", "title_1", "author_1", "content_1"),
                new PostDto("Test_2", "title_2", "author_2", "content_2")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("title_1")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        String id = "test";
        given(getPostService.getPostDto(id))
                .willReturn(new PostDto(id, "title_1", "author_1", "content_1"));

        mockMvc.perform(get("/posts" + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("author_1")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        String id = "test";

        given(getPostService.getPostDto(id)).willThrow(new postNotFound());

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                "title": "tttt",
                "author": "jyh",
                "content": "cccc"
                }
                """;

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createPostService).create(any());
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        String body = """
                {
                "title": "tttt",
                "content": "cccc"
                }
                """;
        String postId = "ID001";

        mockMvc.perform(patch("/posts/" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        verify(updatePostService).update(eq(postId), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String postId = "ID001";

        mockMvc.perform(delete("/posts/" + postId))
                .andExpect(status().isOk());

        verify(deletePostService).delete(postId);
    }
}
