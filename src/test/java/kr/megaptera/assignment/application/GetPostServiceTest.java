package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostAuthor;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {
    private PostRepository postRepository;

    private GetPostService getPostService;
    private String postId = "0001POST";

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }
    @Test
    @DisplayName("게시물 조회")
    void detail() {
        given(postRepository.find(PostId.of(postId)))
                .willReturn(new Post(
                        PostId.of("0001POST"),
                        PostTitle.of("제목"),
                        PostAuthor.of("작성자"),
                        MultilineText.of("내용")
                ));

        PostDto postDto = getPostService.getPostDto(postId);

        assertThat(postDto).isNotNull();
    }
}
