package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

//    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        Post post = Post.builder()
                .id("001")
                .title("title")
                .content("content")
                .author("author")
                .build();

        Post post2 = Post.builder()
                .id("002")
                .title("title2")
                .content("content2")
                .author("author2")
                .build();

        given(getPostsService.getPostList()).willReturn(List.of(
                new PostDto(post),
                new PostDto(post2)
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(post.getTitle())
                ));
    }

//    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws PostNotFound, Exception {

        Post post = Post.builder()
                .id("001")
                .title("title")
                .content("content")
                .author("author")
                .build();

        given(getPostService.getPost("001")).willReturn(new PostDto(post));

        mockMvc.perform(get("/posts/" + post.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString(post.getTitle())
                ));
    }

//    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws PostNotFound, Exception {

        Post post = Post.builder()
                .id("001")
                .title("title")
                .content("content")
                .author("author")
                .build();

        given(getPostService.getPost("001")).willReturn(new PostDto(post));

        mockMvc.perform(get("/posts/ + " + post.getId()))
                .andExpect(status().isNotFound());

    }

//    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = "{\"title\":\"title\",\"content\":\"content\",\"author\":\"author\"}";

        mockMvc.perform(post("/posts")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated());

        verify(createPostService).createPost(any(PostDto.class));
    }

//    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception, PostNotFound {
        String id = "001";
        String json = "{\"title\":\"title\",\"content\":\"content\",\"author\":\"author\"}";

        mockMvc.perform(patch("/posts/" + id)
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());

        verify(updatePostService).updatePost(eq(id), any(PostDto.class));
    }

//    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception, PostNotFound {

        Post post = Post.builder()
                .id("001")
                .title("title")
                .content("content")
                .author("author")
                .build();
        given(deletePostService.deletePost(post.getId())).willReturn(new PostDto(post));


        mockMvc.perform(delete("/posts/" + post.getId()))
                .andExpect(status().isOk());

        verify(deletePostService).deletePost(post.getId());

    }
}
