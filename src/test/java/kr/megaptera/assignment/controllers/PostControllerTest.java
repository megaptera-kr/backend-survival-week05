package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GetPostsService getPostsService;
    @MockBean
    private GetPostService getPostService;
    @MockBean
    private CreatePostService createPostService;
    @MockBean
    private UpdatePostService updatePostService;
    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());

        verify(getPostsService).list();
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        given(getPostService.get(any(String.class)))
                .willReturn(new PostDto("0001POST", "title1", "author1", "content1"));

        mockMvc.perform(get("/posts/0001POST"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("0001POST"))
                .andExpect(jsonPath("title").value("title1"))
                .andExpect(jsonPath("author").value("author1"))
                .andExpect(jsonPath("content").value("content1"));

        verify(getPostService).get("0001POST");
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        given(getPostService.get(any(String.class)))
                .willThrow(new PostNotFound());

        mockMvc.perform(get("/posts/0001POST"))
                .andExpect(status().isNotFound());

        verify(getPostService).get("0001POST");
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        PostDto createdPost = new PostDto("0001POST", "title1", "author1", "content1");

        given(createPostService.create(any(PostCreateDto.class)))
                .willReturn(createdPost);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "title": "title1",
                        "author": "author1",
                        "content": "content1"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("title").value("title1"))
                .andExpect(jsonPath("author").value("author1"))
                .andExpect(jsonPath("content").value("content1"));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        PostDto updatedPost = new PostDto("0001POST", "title1", "author1", "content1");

        given(updatePostService.update(any(String.class), any(PostUpdateDto.class)))
                .willReturn(updatedPost);

        mockMvc.perform(patch("/posts/0001POST")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "title": "title1",
                        "content": "content1"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("title1"))
                .andExpect(jsonPath("author").value("author1"))
                .andExpect(jsonPath("content").value("content1"));
    }


    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        PostDto deletedPost = new PostDto("0001POST", "title1", "author1", "content1");

        given(deletePostService.delete("0001POST"))
                .willReturn(deletedPost);

        mockMvc.perform(delete("/posts/0001POST"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("0001POST"))
                .andExpect(jsonPath("title").value("title1"))
                .andExpect(jsonPath("author").value("author1"))
                .andExpect(jsonPath("content").value("content1"));

        verify(deletePostService).delete("0001POST");
    }
}
