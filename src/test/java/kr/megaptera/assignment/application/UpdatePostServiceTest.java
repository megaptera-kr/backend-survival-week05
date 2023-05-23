package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void update() {
        PostId postId = new PostId("001POST");

        Post post =
                new Post(postId, "제목", "작성자", new MultilineText("내용"));

        given(postRepository.find(postId)).willReturn(post);

        PostUpdateDto postUpdateDto =
                new PostUpdateDto("변경된 제목", "변경된 내용");

        updatePostService.updatePost(postId.toString(), postUpdateDto);

        assertThat(post.title()).isEqualTo("변경된 제목");
        assertThat(post.content()).isEqualTo(new MultilineText("변경된 내용"));
    }
}
