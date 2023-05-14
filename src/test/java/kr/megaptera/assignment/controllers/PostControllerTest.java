package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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

    private static final class TestPost {
        private static final PostDto post = new PostDto("1", "제목", "저자", "내용");
    }

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        given(getPostsService.posts()).willReturn(List.of(TestPost.post));

        mockMvc.perform(get("/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"title\":\"제목\"," +
                    "\"author\":\"저자\"," +
                    "\"content\":\"내용\"" +
                    "}")
            ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        given(getPostService.post(any())).willReturn(TestPost.post);

        mockMvc.perform(get("/posts/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"title\":\"제목\"," +
                    "\"author\":\"저자\"," +
                    "\"content\":\"내용\"" +
                    "}")
            ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        given(getPostService.post(any())).willThrow(PostNotFound.class);

        mockMvc.perform(get("/posts/100000"))
            .andExpect(status().isNotFound())
            .andExpect(content().string(
                containsString("게시글을 찾을 수 없습니다.")
            ));
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        given(createPostService.createPost(any())).willReturn(TestPost.post);

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"title\":\"제목\"," +
                    "\"author\":\"저자\"," +
                    "\"content\":\"내용\"" +
                    "}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"title\":\"제목\"," +
                    "\"author\":\"저자\"," +
                    "\"content\":\"내용\"" +
                    "}")
            ));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        PostDto post = new PostDto("1", "수정 제목", "저자", "수정 내용");
        given(updatePostService.updatePost(any(), any())).willReturn(post);

        mockMvc.perform(patch("/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"title\":\"수정 제목\"," +
                    "\"content\":\"수정 내용\"" +
                    "}"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"title\":\"수정 제목\"," +
                    "\"author\":\"저자\"," +
                    "\"content\":\"수정 내용\"" +
                    "}")
            ));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        given(deletePostService.deletePost(any())).willReturn(TestPost.post);

        mockMvc.perform(delete("/posts/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"title\":\"제목\"," +
                    "\"author\":\"저자\"," +
                    "\"content\":\"내용\"" +
                    "}")
            ));
    }
}
