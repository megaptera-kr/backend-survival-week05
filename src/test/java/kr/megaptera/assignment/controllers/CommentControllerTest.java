package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.*;
import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    @DisplayName("GET /comments")
    void list() throws Exception {
        String postId = "001";

        given(getCommentsService.getCommentDtos(postId))
                .willReturn(List.of(new CommentDto("0001","author","content1")));

        mockMvc.perform(get("/comments?postId="+postId))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("content1")
                ));
    }

    @Test
    @DisplayName("Post /comments")
    void create() throws Exception {
        String postId = "001";
        String json = """
                {
                "title" : "화이팅 합시다.",
                "author" : "구성훈"
                }
                """;
        mockMvc.perform(post("/comments?postId="+postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isCreated());

        verify(createCommentService).createCommentDto(any(),any(CommentCreateDto.class));
    }

    @Test
    @DisplayName("PATCH /comments/{id}")
    void update() throws Exception {
        String commentId = "001";
        String postId = "0001";

        String json = """
                {
                  "content1": "comment1"
                }
                """;

        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());

        verify(updateCommentService)
                .updateComment(eq(commentId),
                        eq(postId),
                        any(CommentUpdateDto.class)
                );
    }


    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String commentId = "0001";
        String postId = "001";

        mockMvc.perform(delete("/comments/"+commentId+"?postId="+postId))
                .andExpect(status().isOk());

        verify(deleteCommentService).deleteComment(commentId,postId);
    }
}
