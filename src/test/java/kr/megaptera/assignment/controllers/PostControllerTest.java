package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // - Spring boot replace(create and inject) the actual bean with the mock bean
    @MockBean
    private GetPostsService getPostsService;
    @MockBean
    private GetPostService getPostService;
    @MockBean
    private UpdatePostService updatePostService;
    @MockBean
    private CreatePostService createPostService;
    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        given(getPostsService.list()).willReturn(List.of(
                PostDto.from(new Post("title1", "author1", MultilineText.from("content1"))),
                PostDto.from(new Post("title2", "author2", MultilineText.from("content2")))
        ));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title1")))
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("title2")))
                .andExpect(content().string(containsString("author2")));
        verify(getPostsService).list();
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        Post post = new Post("title1", "author1", MultilineText.from("content1"));
        given(getPostService.detail(post.id().toString())).willReturn(PostDto.from(post));

        mockMvc.perform(get("/posts/" + post.id()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title1")))
                .andExpect(content().string(containsString("author1")));
        verify(getPostService).detail(post.id().toString());
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        given(getPostService.detail("non-existing-id")).willThrow(PostNotFoundException.class);

        mockMvc.perform(get("/posts/non-existing-id"))
                .andExpect(status().isNotFound());
        verify(getPostService).detail("non-existing-id");
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        // Arrange
        String json = """
                {
                    "title": "title1",
                    "author": "author1",
                    "content": "content1"
                }
                """;
        PostDto expected = PostDto.from(new Post("title1", "author1", MultilineText.from("content1")));
        when(createPostService.create(any(PostDto.class))).thenReturn(expected);
        // Act
        MvcResult result = mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();
        // Assert
        ArgumentCaptor<PostDto> postDtoArgumentCaptor = ArgumentCaptor.forClass(PostDto.class);
        verify(createPostService).create(postDtoArgumentCaptor.capture());
        PostDto postDto = postDtoArgumentCaptor.getValue();
        assertThat(postDto.getTitle()).isEqualTo("title1");
        assertThat(postDto.getAuthor()).isEqualTo("author1");
        assertThat(postDto.getContent()).isEqualTo("content1");

        String jsonResponse = result.getResponse().getContentAsString();
        assertThat(jsonResponse).contains("title1");
        assertThat(jsonResponse).contains("author1");
        assertThat(jsonResponse).contains("content1");
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        // Arrange
        Post post = new Post("title1", "author1", MultilineText.from("content1"));
        String postId = post.id().toString();
        String json = """
                {
                    "title": "update",
                    "author": "update author",
                    "content": "update content"
                }
                """;
        // Act
        mockMvc.perform(patch("/posts/" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        // Assert
        ArgumentCaptor<PostDto> postDtoArgumentCaptor = ArgumentCaptor.forClass(PostDto.class);
        verify(updatePostService).update(eq(postId), postDtoArgumentCaptor.capture());
        PostDto requestBody = postDtoArgumentCaptor.getValue();
        assertThat(requestBody.getTitle()).isEqualTo("update");
        assertThat(requestBody.getAuthor()).isEqualTo("update author");
        assertThat(requestBody.getContent()).isEqualTo("update content");
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        // Arrange
        Post post = new Post("title1", "author1", MultilineText.from("content1"));
        String postId = post.id().toString();
        // Act
        mockMvc.perform(delete("/posts/" + postId))
                .andExpect(status().isOk());
        // Assert
        verify(deletePostService).delete(postId);
    }
}
