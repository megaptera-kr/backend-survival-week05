package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.controllers.dtos.PostUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
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
        given(getPostsService.findAll()).willReturn(List.of(
                new PostDto("001", "a1", "b1", "c1"),
                new PostDto("002", "a2", "b2", "c2")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("a1")
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void find_by_id() throws Exception {
        String id = "001";

        given(getPostService.findById(id))
                .willReturn(new PostDto(id, "a", "b", "c"));

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("c")
                ));
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                    "title":"a",
                    "author":"b",
                    "content":"c"
                }
                """;
        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        String id = "001";

        String json = """
                {
                    "title":"dev",
                    "content":"fighting"
                }
                """;

        mockMvc.perform(patch("/posts/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(updatePostService)
                .updatePost(eq(id), any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "001";

        mockMvc.perform(delete("/posts/" + id))
                .andExpect(status().isOk());

        verify(deletePostService).deletePost(id);
    }
}
