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
import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CreateCommentResponse;
import kr.megaptera.assignment.dtos.DeleteCommentResponse;
import kr.megaptera.assignment.dtos.GetCommentListResponse;
import kr.megaptera.assignment.dtos.UpdateCommentRequest;
import kr.megaptera.assignment.dtos.UpdateCommentResponse;
import kr.megaptera.assignment.models.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCommentService createCommentService;
    @MockBean
    private GetCommentsService getCommentsService;
    @MockBean
    private UpdateCommentService updateCommentService;
    @MockBean
    private DeleteCommentService deleteCommentService;

    @Test
    @DisplayName("GET /comments?postId={id}")
    void list() throws Exception {
        // given
        given(getCommentsService.getCommentList(1L)).willReturn(
                List.of(new GetCommentListResponse("1", "1", "Harry", "내용")));

        // when, then
        mockMvc.perform(get("/comments?postId=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("내용")));

    }

    @Test
    @DisplayName("POST /comments?postId={id}")
    void create() throws Exception {
        // given
        String json = """
                {
                "author": "Harry",
                "content": "내용"
                }
                """;
        Comment request = new Comment(1L, "Harry", "내용");
        given(createCommentService.createComment(request))
                .willReturn(new CreateCommentResponse("1", "1", request.getAuthor(),
                        request.getContent()));

        // when, then
        mockMvc.perform(post("/comments?postId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void update() throws Exception {
        // given
        String json = """
                {
                "content": "내용바뀜"
                }
                """;
        UpdateCommentRequest request = new UpdateCommentRequest("내용바뀜");
        given(updateCommentService.updateComment(1L, 1L, request))
                .willReturn(new UpdateCommentResponse("1", "1", "Harry",
                        request.getContent()));

        // when, then
        mockMvc.perform(patch("/comments/1?postId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deleteComment() throws Exception {
        // given
        given(deleteCommentService.deleteComment(1L, 1L)).willReturn(
                new DeleteCommentResponse("1", "1", "Harry", "내용"));

        // when, then
        mockMvc.perform(delete("/comments/1?postId=1"))
                .andExpect(status().isOk());
    }
}
