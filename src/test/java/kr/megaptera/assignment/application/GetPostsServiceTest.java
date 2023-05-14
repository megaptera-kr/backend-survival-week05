package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostsServiceTest {
    private PostRepository postRepository;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        given(postRepository.findAll())
                .willReturn(List.of(new Post(
                        new PostId("0001POST"),
                        "제목",
                        "작성자",
                        new MultilineText("내용")
                )));

        List<PostDto> postDtos = getPostsService.getPostDtos();

        assertThat(postDtos).hasSize(1);
    }
}
