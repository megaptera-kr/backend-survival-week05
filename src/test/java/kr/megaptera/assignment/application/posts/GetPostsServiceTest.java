package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.entities.PostEntity;
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
        var postEntities = List.of(
                new PostEntity(
                        "0001POST",
                        "제목",
                        "작성자",
                        "내용"));

        given(postRepository.findAll()).willReturn(postEntities);
        List<PostReadDto> postDtos = getPostsService.execute().stream().toList();
        assertThat(postDtos).hasSize(1);
    }
}
