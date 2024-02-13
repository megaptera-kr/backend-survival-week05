package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetCommentsService getCommentsService;
    @MockBean
    private CreateCommentService createCommentService;
    @MockBean
    private UpdateCommentService updateCommentService;
    @MockBean
    private DeleteCommentService deleteCommentService;


    @Test
    @DisplayName("GET /comments?postId={postId}")
    void list() throws Exception {
        // Arrange
        String postId = "1";
        given(getCommentsService.list(postId)).willReturn(List.of(
                CommentDto.from(new Comment("author1", MultilineText.from("content1"))),
                CommentDto.from(new Comment("author2", MultilineText.from("content2")))
        ));
        // Act
        mockMvc.perform(get("/comments?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("content1")))
                .andExpect(content().string(containsString("author2")))
                .andExpect(content().string(containsString("content2")));
        // Assert
        verify(getCommentsService).list(postId);
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void create() throws Exception {
        // Arrange
        String postId = "1";
        String jsonBody = """
                {
                    "author": "author1",
                    "content": "content1"
                }
                """;
        // Act
        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());
        // Assert
        verify(createCommentService).create(eq(postId), any(CommentDto.class));
    }

    @Test
    @DisplayName("PATCH /comments/{id}")
    void update() throws Exception {
        // Arrange
        String id = "1";
        String postId = "1";
        String jsonBody = """
                {
                    "author": "author1",
                    "content": "content1"
                }
                """;
        // Act
        mockMvc.perform(patch("/comments/" + id + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
        // Assert
        verify(updateCommentService).update(eq(id), eq(postId), any(CommentDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        // Arrange
        String id = "1";
        String postId = "1";
        // Act
        mockMvc.perform(delete("/comments/" + id + "?postId=" + postId))
                .andExpect(status().isOk());
        // Assert
        verify(deleteCommentService).delete(eq(id), eq(postId));
    }
}
