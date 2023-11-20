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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdatePostServiceTest {
    private final String ID_TO_UPDATE = "AAA";
    private final String CONST_AUTHOR = "writer";
    private final String UPDATED_TITLE = "modifiedTitle";
    private final String UPDATED_CONTENT = "modifiedContent";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // 여기서 데이터를 삽입하거나 Mocking 등을 수행
        postRepository.save(new Post(PostId.of(ID_TO_UPDATE), PostTitle.of("originalTitle"), PostAuthor.of(CONST_AUTHOR), MultilineText.of("originalContent")));
    }

    @Test
    @DisplayName("게시물 수정")
    void update() throws Exception {
        String json = String.format("""
                {
                	"title": "%s",
                	"content": "%s"
                }
                """, UPDATED_TITLE, UPDATED_CONTENT);
        this.mockMvc.perform(
                        patch("/posts/" + ID_TO_UPDATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk());

        Post postUpdated = postRepository.find(PostId.of(ID_TO_UPDATE));

        assertThat(new Post(PostId.of(ID_TO_UPDATE), PostTitle.of(UPDATED_TITLE), PostAuthor.of(CONST_AUTHOR), MultilineText.of(UPDATED_CONTENT)))
                .isEqualTo(postUpdated);
    }
}
