package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetPostsServiceTest {

    private PostRepository postRepository;

    private GetPostsService getPostsService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        given(postRepository.findAll())
                .willReturn(List.of(
                        new Post(PostId.of("id_01"), "title", "author", PostContent.of("content_01")),
                        new Post(PostId.of("id_02"), "title", "author", PostContent.of("content_02"))
                ));

        List<Post> posts = postRepository.findAll();

        assertThat(postRepository.findAll().equals(posts));
        assertThat(posts).hasSize(2);
    }
}
