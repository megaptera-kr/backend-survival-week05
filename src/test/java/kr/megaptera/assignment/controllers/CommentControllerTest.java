package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.comment.*;
import kr.megaptera.assignment.dtos.comment.*;
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

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    // TODO: PostControllerTest 를 참고해서 테스트를 작성해 주세요.
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetCommentsService getCommentsService;

    @MockBean
    CreateCommentService createCommentService;

    @MockBean
    UpdateCommentService updateCommentService;

    @MockBean
    DeleteCommentService deleteCommentService;

    @Test
    @DisplayName("GET /comments")
    void list() throws Exception {
        String postId = "ABC001";

        given(getCommentsService.getCommentDtos(postId)).willReturn(List.of(
                new CommentDto("id_1", "jyh", "content"),
                new CommentDto("id_2", "bjs", "content_2")
        ));

        mockMvc.perform(get("/comments?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("id_1")
                ));
    }

    @Test
    @DisplayName("POST /comments")
    void create() throws Exception {
        String postId = "po_001";
        String body = """
                {
                "author": "jyh",
                "content": "hahaha"
                }
                """;

        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isOk()
                );

        verify(createCommentService).create(any(), any(CommentCreateDto.class));

    }

    @Test
    @DisplayName("PATCH /comments/{id}")
    void update() throws Exception {
        String commentId = "co_001";
        String postId = "po_001";
        String body = """
                {
                "content": "hohoho"
                }
                """;

        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        verify(updateCommentService).update(any(), any(), any(CommentUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /comments/{id}")
    void deleteComment() throws Exception {
        String commentId = "co_001";
        String postId = "po_001";

        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + postId))
                .andExpect(status().isOk());

        verify(deleteCommentService).delete(commentId, postId);
    }
}
