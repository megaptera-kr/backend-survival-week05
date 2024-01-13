package kr.megaptera.assignment.application;

import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreatePostServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PostRepository postRepository;

    @Test
    @DisplayName("게시물 생성")
    void create() throws Exception {
        String json = """
                {"title": "the title", "author": "the author", "content": "the content"}
                """;
        mockMvc
                .perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(postRepository).save(argThat(post -> post.getAuthor().text().equals("the author")));
    }

    @Test
    @DisplayName("게시물 생성 2")
    void create2() throws Exception {
        String json = """
                {"title": "the title", "author": "the author", "content": "the content"}
                """;
        mockMvc
                .perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isCreated());

        verify(postRepository).save(argThat(post -> !post.getAuthor().text().equals("the author2")));
    }
}
