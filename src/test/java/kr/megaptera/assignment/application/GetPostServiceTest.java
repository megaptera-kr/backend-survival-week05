package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {
    private PostRepository postRepository;
    private GetPostService getPostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }
    @Test
    @DisplayName("게시물 조회")
    void detail() {
        PostId postId = new PostId("1");
        given(postRepository.find(postId))
                .willReturn(new Post
                            (postId,
                                "제목",
                                "작성자",
                                "내용무"));

        PostDto postdto = getPostService.getPostDto(postId.toString());
        assertThat(postdto.getId()).isEqualTo(postId.toString());
        assertThat(postdto.getAuthor()).isEqualTo("작성자");
    }
}
