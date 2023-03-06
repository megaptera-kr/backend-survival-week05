package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;

import kr.megaptera.assignment.dtos.GetPostResponse;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(GetPostService.class)
class GetPostServiceTest {

    private GetPostService sut;
    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        sut = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() {
        // given
        Post expected = new Post(1L, "제목1", "Harry", "내용1");
        given(postRepository.findById(1L)).willReturn(expected);

        // when
        GetPostResponse post = sut.getPost(1L);

        // then
        assertThat(post.getId()).isEqualTo(expected.getId().toString());
        assertThat(post.getTitle()).isEqualTo(expected.getTitle());
        assertThat(post.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(post.getContent()).isEqualTo(expected.getContent());
    }

    @DisplayName("게시물 조회 실패시 익셉션이 발생한다.")
    @Test
    void detailFail() {
        // given
        given(postRepository.findById(1L)).willReturn(null);

        // when, then
        assertThatCode(()->sut.getPost(1L))
                .isInstanceOf(PostNotFoundException.class)
                .hasMessage("Post가 존재하지 않습니다.");
    }
}
