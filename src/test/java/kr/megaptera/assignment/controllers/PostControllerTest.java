package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    GetPostsService getPostsService;
    @MockBean
    CreatePostService createPostService;
    @MockBean
    GetPostService getPostService;
    @MockBean
    DeletePostService deletePostService;

    @MockBean
    UpdatePostService updatePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        
        given(getPostsService.getPosts()).willReturn(List.of(
                new PostDto("1", "제목", "작성자", "글 내용")
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("제목")));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        String id = "3";

        given(getPostService.getPost(id)).willReturn(new PostDto(id, "제목만듬", "작성자만듬", "내용만듬"));

        mockMvc.perform(get("/posts/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("제목")));

        PostDto postDto = getPostService.getPost(id);

        assertThat(postDto).isNotNull();
        assertThat(postDto.getId()).isEqualTo(id);
        assertThat(postDto.getTitle()).isEqualTo("제목만듬");
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {

        String id = "1000";

        given(getPostService.getPost(id)).willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/{id}",  id))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                    "title" : "새 글",
                    "author" : "작성자",
                    "content" : "내용"
                }
                """;
        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        //verify(createPostService).create(any(PostDto.class));

    }


    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {

        String id = "1";
        String json = """
                {
                    "title" : "가가가",
                    "content" : "나나나"
                }
                """;

        mockMvc.perform(patch("/posts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());

        verify(updatePostService).updatePost(eq(id), any(PostDto.class));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "100";

        mockMvc.perform(delete("/posts/{id}", id))
                .andExpect(status().isOk());

        verify(deletePostService).deletePost(id);
    }
}
