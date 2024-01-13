package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.SingleLineText;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PostRepository postRepository;

    @Autowired
    private PostController postController;

    private List<PostId> postIdList;

    @BeforeEach
    void setUp() {
        given(postRepository.findAll()).willReturn(
                IntStream.range(0, 10)
                        .mapToObj(i -> Post.createNew(
                                SingleLineText.of("Title: " + i),
                                SingleLineText.of("Author: " + i),
                                MultiLineText.of("Content: " + i)
                        ))
                        .toList()
        );

        postIdList = IntStream.range(0, 5).mapToObj(o -> PostId.generate()).toList();
        for (int i = 0; i < postIdList.size(); i++) {
            PostId postId = postIdList.get(i);
            given(postRepository.find(postId)).willReturn(
                    Post.createNew(
                            SingleLineText.of("Title: " + i),
                            SingleLineText.of("Author: " + i),
                            MultiLineText.of("Content: " + i)
                    )
            );
        }
    }

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
        verify(postRepository).findAll();
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        mockMvc.perform(get("/posts/" + postIdList.get(0).id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title: 0"))
                .andDo(print());

    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        mockMvc.perform(get("/posts/" + PostId.generate().id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist())
                .andDo(print());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String json = """
                {
                    "author": "hello",
                    "title": "world",
                    "content": "from here"
                }
                """;
        mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
        verify(postRepository).save(argThat(it -> it.getTitle().text().equals("world")));
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        String json = """
                   {
                        "title": "Modified title",
                        "content": "modified content"
                   }
                """;
        mockMvc.perform(patch("/posts/" + postIdList.get(0).id()).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
        verify(postRepository).save(argThat(it -> it.getTitle().text().equals("Modified title")));
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        mockMvc.perform(delete("/posts/" + postIdList.get(0).id()))
                .andExpect(status().isOk());

        verify(postRepository).delete(argThat(it -> it.id().equals(postIdList.get(0).id())));
    }
}
