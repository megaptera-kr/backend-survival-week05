package kr.megaptera.assignment.application;


import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostAuthor;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeletePostServiceTest {
    private final String ID_TO_DELETE = "AAA";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // 여기서 데이터를 삽입하거나 Mocking 등을 수행
        postRepository.save(new Post(PostId.of(ID_TO_DELETE), PostTitle.of("새 글"), PostAuthor.of("writer"), MultilineText.of("content1")));
    }

    @Test
    @DisplayName("게시물 삭제")
    void deleteTest() throws Exception {
        int oldSize = postRepository.findAll().size();
        this.mockMvc.perform(
                        delete("/posts/"+ID_TO_DELETE))
                .andExpect(status().isOk());

        int newSize = postRepository.findAll().size();

        assertThat(newSize).isEqualTo(oldSize - 1);
    }
}
