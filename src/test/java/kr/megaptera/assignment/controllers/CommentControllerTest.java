package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    // TODO: PostControllerTest 를 참고해서 테스트를 작성해 주세요.

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

//    @Test
    @DisplayName("GET /comments?postId={postId}")
    void list() throws Exception {

        Comment comment = Comment.builder()
                .id("001")
                .postId("001")
                .content("content")
                .author("author")
                .build();

        Comment comment2 = Comment.builder()
                .id("2")
                .postId("1")
                .content("content2")
                .author("author2")
                .build();

        given(getCommentsService.getCommentList(comment.getPostId())).willReturn(List.of(
                new CommentDto(comment),
                new CommentDto(comment2)
        ));

        mockMvc.perform(get("/comments?postId=" + comment.getPostId()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("content")
                ));
    }

//    @Test
    @DisplayName("POST /comments")
    void create() throws Exception {
        String commentId = "001";
        String postId = "001";

        Comment comment = Comment.builder()
                .id(commentId)
                .postId(postId)
                .content("content")
                .author("author")
                .build();

        String json = "{\"content\":\"content\",\"author\":\"author\"}";

        mockMvc.perform(post("/comments?postId=" + comment.getPostId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(createCommentService).createComment(
                any(),
                any(CommentDto.class)
        );
    }

//    @Test
    @DisplayName("PATCH /comments/{id}")
    void update() throws Exception, CommentNotFound {
        String commentId = "001";
        String postId = "001";

        String json = "{\"content\":\"content\"}";


        mockMvc.perform(patch("/comments/" + commentId + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isNoContent());

        verify(updateCommentService)
                .updateComment(eq(commentId),
                        eq(postId),
                        any(CommentDto.class)
                );
    }

//    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception, CommentNotFound {

        String commentId = "001";
        String postId = "001";
        mockMvc.perform(delete("/comments/" + commentId + "?postId=" + postId))
                .andExpect(status().isOk());

        verify(deleteCommentService).deleteComment(commentId, postId);
    }

}
