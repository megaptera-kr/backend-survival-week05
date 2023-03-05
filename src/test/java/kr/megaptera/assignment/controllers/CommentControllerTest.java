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
    void getComments() throws Exception {
        String postId = "POST_ID_01";
        given(getCommentsService.getCommentDtos(postId))
            .willReturn(List.of(
                    new CommentDto("COMMENT_001","POST_ID_01","AUTHOR_01","CONTENT_01"),
                    new CommentDto("COMMENT_002","POST_ID_01","AUTHOR_02","CONTENT_02")
            ));

        this.mockMvc.perform(get("/comments?postId="+postId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("COMMENT_001")));

        verify(getCommentsService).getCommentDtos(postId);
    }

    @Test
    @DisplayName("GET /comments?postId={postId} - with correct ID")
    void getCommentsWithCorrectId() throws Exception {
        String postId = "POST_ID_03";
        given(getCommentsService.getCommentDtos(postId))
                .willReturn(List.of(
                        new CommentDto("COMMENT_001","POST_ID_01","AUTHOR_01","CONTENT_01"),
                        new CommentDto("COMMENT_002","POST_ID_01","AUTHOR_02","CONTENT_02")
                ));

        this.mockMvc.perform(get("/comments?postId="+postId))
                .andExpect(status().isOk());

        verify(getCommentsService).getCommentDtos(postId);
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void createComment() throws Exception {
        String postId = "POST_ID_01";
        String json = """
                {   
                    "author" : "AUTHOR_01",
                    "content" : "CONTENT_01"
                }
                """;
        mockMvc.perform(post("/comments?postId="+postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createCommentService).createComment(eq(postId),any(CommentCreateDto.class));
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void updateComment() throws Exception {
        String id = "COMMENT_ID_01";
        String postId   = "POST_ID_01";
        String json = """
                {
                    "title":"TITLE_CHANGE",
                    "CONTENT":"CONTENT_CHANGE"
                }
                """;
        mockMvc.perform(patch("/comments/"+id+"?postId="+postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(updateCommentService).updateComment(eq(id), eq(postId), any(CommentUpdateDto.class));
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deleteComment() throws Exception {
        String id = "COMMENT_ID_01";
        String postId   = "POST_ID_01";

        mockMvc.perform(delete("/comments/"+id+"?postId="+postId))
                .andExpect(status().isOk());

        verify(deleteCommentService).deletePost(id,postId);
    }
}
