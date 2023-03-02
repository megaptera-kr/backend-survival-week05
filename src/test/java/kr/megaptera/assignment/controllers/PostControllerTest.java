package kr.megaptera.assignment.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.CreatePostResponse;
import kr.megaptera.assignment.dtos.DeletePostResponse;
import kr.megaptera.assignment.dtos.GetPostListResponse;
import kr.megaptera.assignment.dtos.GetPostResponse;
import kr.megaptera.assignment.dtos.UpdatePostRequest;
import kr.megaptera.assignment.dtos.UpdatePostResponse;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    void list() throws Exception {
        // given
        given(getPostsService.getPosts()).willReturn(
                List.of(new GetPostListResponse("1", "제목", "Harry", "내용")));

        // when, then
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("제목")));

    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        // given
        given(getPostService.getPost(1L)).willReturn(new GetPostResponse("1", "제목",
                "Harry", "내용"));

        // when, then
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("제목")));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        // given
        given(getPostService.getPost(1L)).willThrow(PostNotFoundException.class);

        // when, then
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        // given
        String json = """
                {
                "title": "제목",
                "author": "Harry",
                "content": "내용"
                }
                """;
        Post request = new Post("제목", "Harry", "내용");
        given(createPostService.createPost(request))
                .willReturn(new CreatePostResponse("1", request.getTitle(), request.getAuthor(),
                        request.getContent()));

        // when, then
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        // given
        String json = """
                {
                "title": "제목바뀜",
                "content": "내용바뀜"
                }
                """;
        UpdatePostRequest request = new UpdatePostRequest("제목바뀜", "내용바뀜");
        given(updatePostService.updatePost(1L, request))
                .willReturn(new UpdatePostResponse("1", request.getTitle(), "Harry",
                        request.getContent()));

        // when, then
        mockMvc.perform(patch("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        // given
        given(deletePostService.deletePost(1L)).willReturn(new DeletePostResponse("1", "제목",
                "Harry", "내용"));

        // when, then
        mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isOk());
    }
}
