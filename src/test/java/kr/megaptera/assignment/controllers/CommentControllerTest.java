package kr.megaptera.assignment.controllers;



import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    // TODO: PostControllerTest 를 참고해서 테스트를 작성해 주세요.
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    GetCommentsService getCommentsService;
    @MockBean
    CreateCommentService createCommentService;

    @MockBean
    DeleteCommentService deleteCommentService;
    @MockBean
    UpdateCommentService updateCommentService;

    static String postId = "1";

    @Test
    @DisplayName("GET /comments")
    void list() throws Exception {

        given(getCommentsService.getComments(postId)).willReturn(List.of(
                new CommentDto("01", "하하하", "날이좋네")
        ));

        mockMvc.perform(get("/comments")
                        .param("postId", postId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("하하하")));
    }

    @Test
    @DisplayName("POST /comments")
    void create() throws Exception {
        String json = """
                {
                    "author" : "작성자",
                    "content" : "내용"
                }
                """;


        mockMvc.perform(post("/comments")
                        .param("postId", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

        verify(createCommentService).create(any(), any(CommentDto.class));

    }


    @Test
    @DisplayName("PATCH /comment/{id}")
    void update() throws Exception {
        String id = "1";
        String json = """
                {                  
                    "content" : "나나나"
                }
                """;

        mockMvc.perform(patch("/comments/{id}", id)
                        .param("postId", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());

        verify(updateCommentService).update(eq(id), any(CommentDto.class));
    }

    @Test
    @DisplayName("DELETE /comment/{id}")
    void deletePost() throws Exception {
        String id = "100";

        mockMvc.perform(delete("/comments/{id}", id)
                        .param("postId", postId))
                .andExpect(status().isOk());

        verify(deleteCommentService).delete(id);
    }
}
