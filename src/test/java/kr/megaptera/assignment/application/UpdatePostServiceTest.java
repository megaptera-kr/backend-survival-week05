package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class UpdatePostServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PostRepository postRepository;

    @SpyBean
    private UpdatePostService updatePostService;

    private final List<PostId> postIdList = IntStream.range(0, 10).mapToObj(i -> PostId.generate()).toList();

    @BeforeEach
    void setup() {
        for (int i = 0; i < postIdList.size(); i++) {
            PostId postId = postIdList.get(i);
            postRepository.save(Post.of(
                    postId,
                    SingleLineText.of(String.format("the title %d", i)),
                    SingleLineText.of(String.format("the author %d", i)),
                    MultiLineText.of(String.format("the content %d ", i), postId.id())
            ));
        }
    }

    @Test
    @DisplayName("게시물 수정")
    void update() throws Exception {
        updatePostService.updatePost(
                postIdList.get(0).id(),
                new PostUpdateDto("Title", "Content")
        );

        assertThat(postRepository.find(postIdList.get(0))).satisfies(post -> {
            assertThat(post.getId()).isEqualTo(postIdList.get(0));
            assertThat(post.getContent().text()).isEqualTo("Content");
        });
    }
}
