package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
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

class GetPostServiceTest {
    private PostRepository postRepository;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }


    @Test
    @DisplayName("게시물 조회")
    void detail() {
        given(postRepository.find(PostId.of("0001POST")))
                .willReturn(new Post(
                        PostId.of("0001POST"),
                        "제목",
                        "작성자",
                        new MultilineText("내용")
                ));

        PostDto postDto = getPostService.getPostDto("0001POST");

        assertThat(postDto.getId()).isEqualTo("0001POST");
        assertThat(postDto.getTitle()).isEqualTo("제목");
        assertThat(postDto.getAuthor()).isEqualTo("작성자");
        assertThat(postDto.getContent()).isEqualTo("내용");

    }
}
