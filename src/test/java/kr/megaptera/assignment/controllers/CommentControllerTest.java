package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.comments.CreateCommentService;
import kr.megaptera.assignment.application.comments.DeleteCommentService;
import kr.megaptera.assignment.application.comments.GetCommentsService;
import kr.megaptera.assignment.application.comments.UpdateCommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCommentService createCommentService;
    @MockBean
    private DeleteCommentService deleteCommentService;
    @MockBean
    private GetCommentsService getCommentsService;
    @MockBean
    private UpdateCommentService updateCommentService;

    @Test
    @DisplayName("GET /comments")
    void list() throws Exception {
        String mockPostId = "1";

        this.mockMvc.perform(get("/comments?postId=%s".formatted(mockPostId)))
                .andExpect(status().isOk());

        verify(getCommentsService).findAll(any());
    }

    @Test
    @DisplayName("POST /comments")
    void create() throws Exception {
        String mockPostId = "1";

        String createCommentJson = """
                    {
                        "title":"title",
                        "author":"author",
                        "content":"content"
                    }
                """;

        RequestBuilder requestBuilder = post("/comments?postId=%s".formatted(mockPostId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(createCommentJson);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        verify(createCommentService).create(any(), any());
    }

    @Test
    @DisplayName("PATCH /comments/{id}")
    void update() throws Exception {
        String mockPostId = "1";
        String mockCommentId = "1";

        String updateCommentJson = """
                    {
                        "title":"title",
                        "content":"content"
                    }
                """;

        RequestBuilder requestBuilder = patch("/comments/%s?postId=%s".formatted(mockCommentId, mockPostId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateCommentJson);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(updateCommentService).update(any(), any(), any());
    }

    @Test
    @DisplayName("DELETE /comments/{id}")
    void deleteComment() throws Exception {
        String mockPostId = "1";
        String mockCommentId = "1";

        RequestBuilder requestBuilder = delete("/comments/%s?postId=%s".formatted(mockCommentId, mockPostId));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(deleteCommentService).delete(any(), any());
    }
}
