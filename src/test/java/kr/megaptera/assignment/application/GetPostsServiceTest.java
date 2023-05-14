package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
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


class GetPostsServiceTest {
    private PostRepository postRepository;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        given(postRepository.findAll())
                .willReturn(List.of(new Post(
                        new PostId("1"),
                        new PostTitle("제목"),
                        "작성자",
                        new MultiLineText("내용")
                )));

        List<PostDto> postDtos = getPostsService.findAll();

        assertThat(postDtos).hasSize(1);
    }
}
