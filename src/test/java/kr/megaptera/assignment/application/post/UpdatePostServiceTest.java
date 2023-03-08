package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class UpdatePostServiceTest {
    private PostRepository postRepository;
    private UpdatePostService updatePostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        String postId = "jyh";

        Post post = new Post(PostId.of(postId),
                "title",
                "author",
                PostContent.of("content"));

        given(postRepository.find(postId)).willReturn(post);

        PostDto updated = updatePostService.update(postId, new PostUpdateDto(
                "title",
                "content"
        ));

        assertThat(updated.getTitle().equals("title"));
        assertThat(updated.getContent().equals("content"));
    }
}
