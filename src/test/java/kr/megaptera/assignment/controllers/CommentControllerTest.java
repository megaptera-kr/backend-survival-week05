package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
        List<CommentDto> commentDtos = List.of(new CommentDto("1", "댓글 작성자", "내용"));
        given(getCommentsService.comments(any())).willReturn(commentDtos);

        mockMvc.perform(get("/comments")
                .param("postId", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"author\":\"댓글 작성자\"," +
                    "\"content\":\"내용\"" +
                    "}")
            ));
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void create() throws Exception {
        CommentDto commentDto = new CommentDto("1", "댓글 작성자", "내용");
        given(createCommentService.create(any(), any())).willReturn(commentDto);

        mockMvc.perform(post("/comments")
            .param("postId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"author\":\"댓글 작성자\"," +
                    "\"content\":\"내용\"" +
                    "}"))
            .andExpect(status().isCreated())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"author\":\"댓글 작성자\"," +
                    "\"content\":\"내용\"" +
                    "}")
            ));
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void update() throws Exception {
        CommentDto commentDto = new CommentDto("1", "댓글 작성자", "내용");
        given(updateCommentService.update(any(), any(), any())).willReturn(commentDto);

        mockMvc.perform(patch("/comments/1")
            .param("postId", "1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"content\":\"내용\"" +
                "}"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deleteComment() throws Exception {
        CommentDto commentDto = new CommentDto("1", "댓글 작성자", "내용");

        given(deleteCommentService.delete(any(), any())).willReturn(commentDto);

        mockMvc.perform(delete("/comments/1")
            .param("postId", "1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"content\":\"내용\"" +
                "}"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("{" +
                    "\"id\":\"1\"," +
                    "\"author\":\"댓글 작성자\"," +
                    "\"content\":\"내용\"" +
                    "}")
            ));
    }
}
