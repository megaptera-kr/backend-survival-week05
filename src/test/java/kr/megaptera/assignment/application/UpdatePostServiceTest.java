package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;

import kr.megaptera.assignment.dtos.UpdatePostRequest;
import kr.megaptera.assignment.dtos.UpdatePostResponse;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(UpdatePostService.class)
class UpdatePostServiceTest {

    private UpdatePostService sut;

    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        sut = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        // given
        UpdatePostRequest request = new UpdatePostRequest("제목바뀜", "내용바뀜");
        Post oldPost = new Post(1L, "제목1", "Harry", "내용1");
        given(postRepository.findById(1L)).willReturn(oldPost);

        // when
        UpdatePostResponse post = sut.updatePost(1L, request);

        // then
        assertThat(post.getId()).isEqualTo(oldPost.getId().toString());
        assertThat(post.getTitle()).isEqualTo(request.getTitle());
        assertThat(post.getAuthor()).isEqualTo(oldPost.getAuthor());
        assertThat(post.getContent()).isEqualTo(request.getContent());
    }

    @DisplayName("게시물 조회 실패시 익셉션이 발생한다.")
    @Test
    void updateFail() {
        // given
        UpdatePostRequest request = new UpdatePostRequest("제목바뀜", "내용바뀜");
        given(postRepository.findById(1L)).willReturn(null);

        // when, then
        assertThatCode(() -> sut.updatePost(1L, request))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessage("Post가 존재하지 않습니다.");
    }
}
