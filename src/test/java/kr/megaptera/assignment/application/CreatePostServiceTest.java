package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import kr.megaptera.assignment.dtos.CreatePostResponse;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(CreatePostService.class)
class CreatePostServiceTest {

    private CreatePostService sut;


    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        sut = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        // given
        Post expected = new Post(1L, "제목1", "Harry", "내용1");
        given(postRepository.save(expected)).willReturn(expected);

        // when
        CreatePostResponse post = sut.createPost(expected);

        // then
        assertThat(post.getId()).isEqualTo(expected.getId().toString());
        assertThat(post.getTitle()).isEqualTo(expected.getTitle());
        assertThat(post.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(post.getContent()).isEqualTo(expected.getContent());

    }
}
