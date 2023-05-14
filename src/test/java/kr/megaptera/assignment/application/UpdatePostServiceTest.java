package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.UpdatePostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {
    private UpdatePostService updatePostService;

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        Post post = new Post(PostId.of("1"), "제목", "저자", "내용");
        UpdatePostDto updatePostDto = new UpdatePostDto("수정 후 제목", "수정 후 내용");

        given(postRepository.findPost(any())).willReturn(post);

        assertThat(post.title()).isEqualTo("제목");
        assertThat(post.content()).isEqualTo("내용");

        updatePostService.updatePost(post.id().toString(), updatePostDto);

        assertThat(post.title()).isEqualTo("수정 후 제목");
        assertThat(post.content()).isEqualTo("수정 후 내용");
    }
}
