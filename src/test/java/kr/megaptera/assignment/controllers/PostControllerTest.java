package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.posts.*;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.exceptions.posts.PostNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePostService createPostService;
    @MockBean
    private DeletePostService deletePostService;
    @MockBean
    private GetPostService getPostService;
    @MockBean
    private GetPostsService getPostsService;
    @MockBean
    private UpdatePostService updatePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        this.mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());

        verify(getPostsService).findAll();
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        String mockPostId = "1";

        given(getPostService.find(any())).willReturn(any(PostDto.class));

        this.mockMvc.perform(get("/posts/%s".formatted(mockPostId)))
                .andExpect(status().isOk());

        verify(getPostService).find(any());
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        String mockPostId = "1";

        given(getPostService.find(any())).willThrow(new PostNotFound());

        this.mockMvc.perform(get("/posts/%s".formatted(mockPostId)))
                .andExpect(status().isNotFound());

        verify(getPostService).find(any());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String createPostJson = """
                    {
                        "title":"title",
                        "author":"author",
                        "content":"content"
                    }
                """;

        RequestBuilder requestBuilder = post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createPostJson);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        verify(createPostService).create(any());
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        String mockPostId = "1";

        String updatePostJson = """
                    {
                        "title":"title",
                        "content":"content"
                    }
                """;

        RequestBuilder requestBuilder = patch("/posts/%s".formatted(mockPostId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatePostJson);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(updatePostService).update(any(), any());
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String mockPostId = "1";

        RequestBuilder requestBuilder = delete("/posts/%s".formatted(mockPostId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(deletePostService).delete(any());
    }
}
