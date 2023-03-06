package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import kr.megaptera.assignment.dtos.GetPostListResponse;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(GetPostsService.class)
class GetPostsServiceTest {

    private GetPostsService sut;

    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        sut = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        // given
        List<Post> postList = List.of(new Post(1L, "제목1", "Harry", "내용1"),
                new Post(2L, "제목2", "Harry", "내용2"));

        given(postRepository.findAll()).willReturn(postList);

        // when
        List<GetPostListResponse> posts = sut.getPosts();

        // then
        assertThat(posts.size()).isEqualTo(2);

        for (int i = 0; i < posts.size(); i++) {
            assertThat(posts.get(i).getId()).isEqualTo(postList.get(i).getId().toString());
            assertThat(posts.get(i).getTitle()).isEqualTo(postList.get(i).getTitle());
            assertThat(posts.get(i).getAuthor()).isEqualTo(postList.get(i).getAuthor());
            assertThat(posts.get(i).getContent()).isEqualTo(postList.get(i).getContent());
        }

    }
}
