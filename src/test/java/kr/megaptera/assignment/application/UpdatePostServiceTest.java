package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
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
        PostId postId = new PostId("1");

        Post post =
                new Post(postId, new PostTitle("제목"), "작성자", new MultiLineText("내용"));

        given(postRepository.find(postId.toString())).willReturn(post);

        PostUpdateDto postUpdateDto =
                new PostUpdateDto("update title", "update content");

        updatePostService.update(postId.toString(), postUpdateDto);

        assertThat(post.title()).isEqualTo(new PostTitle("update title"));
        assertThat(post.content()).isEqualTo(new MultiLineText("update content"));
    }
}
