package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.*;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private UpdatePostService updatePostService;

    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        given(getPostsService.getPostDtos()).
                willReturn(List.of(
                        new PostDto("1","title","작성자","내용"),
                        new PostDto("2","제목2","작성자2","내용2")
                ));
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("title")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        String id = "1";
        given(getPostService.getPostDto(id))
                .willReturn(new PostDto(id, "title", "작성자", "내용"));

        mockMvc.perform(get("/posts/"+id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("title")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        String id = "??";

        given(getPostService.getPostDto(id))
                .willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/"+id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                "title" : "화이팅 합시다.",
                "author" : "구성훈"
                }
                """;
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        verify(createPostService).createPost(any(PostCreateDto.class));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        String id = "001";
        String json = """
                {
                  "title": "데브로드",
                  "content": "열심히 합시다"
                }
                """;

        mockMvc.perform(patch("/posts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        verify(updatePostService).update(eq(id), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "001";
        mockMvc.perform(delete("/posts/"+id))
                .andExpect(status().isOk());

        verify(deletePostService).delete(id);
    }
}
