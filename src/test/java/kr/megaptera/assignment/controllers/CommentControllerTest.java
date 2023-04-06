package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.controllers.dtos.CommentCreateDto;
import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
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

    static String postId = "001";

    @Test
    @DisplayName("댓글 목록 조회")
    void list() throws Exception {
        given(getCommentsService.getComments(postId))
                .willReturn(List.of(
                        new CommentDto("001", "a1", "b1", postId),
                        new CommentDto("002", "a2", "b2", postId)
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/comments?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("b1")
                ));
    }

    @Test
    @DisplayName("댓글 등록 테스트")
    void createComment() throws Exception {
        String json = """
                {
                    "author":"a1",
                    "content":"b1"                
                }
                """;

        mockMvc.perform(post("/comments?postId="+postId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isCreated());

        verify(createCommentService).createComment(
                any(),
                any(CommentCreateDto.class)
        );


    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() throws Exception {
        String commentId = "0011";
        String json = """
                {
                    "content" : "c1"                
                }
                """;

        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());

        verify(updateCommentService).updateComment(eq(commentId), eq(postId), any(String.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deleteComment_test() throws Exception {
        String commentId = "0011";

        mockMvc.perform(delete("/comments/"+commentId+"?postId="+postId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(deleteCommentService).deleteComment(eq(commentId), eq(postId));
    }
}
