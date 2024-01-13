package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private CommentRepository commentRepository;
    @Autowired
    private CommentController commentController;

    private final List<PostId> postIdList = IntStream.range(0, 5).mapToObj(o -> PostId.generate()).toList();
    private final List<CommentId> commentIdList = IntStream.range(0, 50).mapToObj(o -> CommentId.generate()).toList();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < postIdList.size(); i++) {
            PostId postId = postIdList.get(i);
            List<Comment> commentList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                int commentIndex = i * 10 + j;
                Comment comment = Comment.of(
                        commentIdList.get(commentIndex),
                        postId,
                        SingleLineText.of("Author " + i + ": " + commentIndex),
                        MultiLineText.of("Content " + i + ": " + commentIndex + ", " + commentIdList.get(commentIndex))
                );
                given(commentRepository.find(commentIdList.get(j), postId)).willReturn(comment);
                commentList.add(comment);
            }
            given(commentRepository.findAll(postId)).willReturn(Collections.unmodifiableList(commentList));
        }
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(get("/comments?postId=" + postIdList.get(0).id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andDo(print());
    }

    @Test
    void create() throws Exception {
        String json = """
                {
                    "author": "Hello",
                    "content": "world!"
                }
                """;
        mockMvc.perform(
                        post("/comments?postId=" + postIdList.get(0).id())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isCreated());
        verify(commentRepository).save(argThat(it -> it.getContent().text().equals("world!")));
    }

    @Test
    void update() throws Exception {
        String json = """
                {
                    "content": "world!!"
                }
                """;
        mockMvc.perform(
                        patch("/comments/" + commentIdList.get(0).id() + "?postId=" + postIdList.get(0).id())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk());
        verify(commentRepository).save(argThat(it -> it.getContent().text().equals("world!!")));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(
                delete("/comments/" + commentIdList.get(0).id() + "?postId=" + postIdList.get(0).id())
        ).andExpect(status().isOk());

        verify(commentRepository).delete(
                argThat(it -> it.equals(postIdList.get(0))),
                argThat(it -> it.equals(commentIdList.get(0)))
        );
    }
}
