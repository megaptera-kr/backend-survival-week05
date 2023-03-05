package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
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
    void list() throws Exception{
        given(getPostsService.getPostDtos())
                .willReturn(List.of(
                        new PostDto("POST_ID_01","TITLE_01","AUTHOR_01","CONTENT_01"),
                        new PostDto("POST_ID_02","TITLE_02","AUTHOR_02","CONTENT_02")
                ));

        this.mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("CONTENT_01")));

        verify(getPostsService).getPostDtos();
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        String id = "POST_ID_03";
        given(getPostsService.getPostDtos())
                .willReturn(List.of(
                        new PostDto("POST_ID_01","TITLE_01","AUTHOR_01","CONTENT_01"),
                        new PostDto("POST_ID_02","TITLE_02","AUTHOR_02","CONTENT_02")
                ));

        this.mockMvc.perform(get("/posts/"+id))
                .andExpect(status().isOk());

        verify(getPostService).getPostDto(id);
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        String id = "POST_ID_01";

        given(getPostsService.getPostDtos())
                .willReturn(List.of(
                        new PostDto("POST_ID_01","TITLE_01","AUTHOR_01","CONTENT_01")
                ));

        this.mockMvc.perform(get("/posts/"+id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TITLE_01")));

        verify(getPostService).getPostDto(id);
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {   "title" : "TITLE_01",
                    "author" : "AUTHOR_01",
                    "content" : "CONTENT_01"
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

        String id   = "POST_ID_01";
        String json = """
                {
                    "title":"TITLE_CHANGE",
                    "CONTENT":"CONTENT_CHANGE"
                }
                """;
        mockMvc.perform(patch("/posts/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(updatePostService).updatePost(id, any(PostUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "POST_ID_001";

        mockMvc.perform(delete("/posts/"+id))
                .andExpect(status().isOk());

        verify(deletePostService).deletePost(id);
    }
}
