package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import kr.megaptera.assignment.dtos.DeletePostResponse;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(DeletePostService.class)
class DeletePostServiceTest {

    private DeletePostService sut;

    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        sut = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        // given
        Post expected = new Post(1L, "제목1", "Harry", "내용1");
        given(postRepository.delete(1L)).willReturn(expected);

        // when
        DeletePostResponse deleted = sut.deletePost(1L);

        // then
        assertThat(deleted.getId()).isEqualTo(expected.getId().toString());
        assertThat(deleted.getTitle()).isEqualTo(expected.getTitle());
        assertThat(deleted.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(deleted.getContent()).isEqualTo(expected.getContent());
    }
}
