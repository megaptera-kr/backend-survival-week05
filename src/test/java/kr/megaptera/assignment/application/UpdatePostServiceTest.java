package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {
    private PostRepository postRepository;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() throws PostNotFound {
        PostId postId = new PostId("001POST");

        Post post =
                new Post(postId, "제목", "작성자", new MultilineText("내용"));

        given(postRepository.find(postId)).willReturn(post);

        PostDto postUpdateDto =
                new PostDto("변경된 제목", "변경된 내용");

        updatePostService.updatePost(postId.toString(), postUpdateDto);

        assertThat(post.title()).isEqualTo("변경된 제목");
        assertThat(post.content()).isEqualTo(new MultilineText("변경된 내용"));
    }
}
