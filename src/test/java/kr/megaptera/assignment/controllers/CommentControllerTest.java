package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    MockMvc mockMvc;

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
        mockMvc.perform(get("/comments?postId=1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void create() throws Exception {
        CommentDto createdComment = new CommentDto("1", "author1", "content1");

        given(createCommentService.create(any(String.class), any(CommentCreateDto.class)))
                .willReturn(createdComment);

        mockMvc.perform(post("/comments?postId=1")
                            .contentType("application/json")
                            .content("""
                            {
                                "author": "author1",
                                "content": "content1"
                            }
                            """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("author").value("author1"))
                .andExpect(jsonPath("content").value("content1"));

        verify(createCommentService).create(any(String.class), any(CommentCreateDto.class));
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void update() throws Exception {
        CommentDto updatedComment = new CommentDto("1", "author1", "content1");

        given(updateCommentService.update(any(String.class), any(String.class), any(CommentUpdateDto.class)))
                .willReturn(updatedComment);

        mockMvc.perform(patch("/comments/1?postId=1")
                            .contentType("application/json")
                            .content("""
                            {
                                "content": "content1"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content").value("content1"));

        verify(updateCommentService).update(any(String.class), any(String.class), any(CommentUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deleteComment() throws Exception {
        CommentDto deletedComment = new CommentDto("1", "author1", "content1");

        given(deleteCommentService.delete(any(String.class), any(String.class)))
                .willReturn(deletedComment);

        mockMvc.perform(delete("/comments/1?postId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("author").value("author1"))
                .andExpect(jsonPath("content").value("content1"));

        verify(deleteCommentService).delete(any(String.class), any(String.class));
    }
}
