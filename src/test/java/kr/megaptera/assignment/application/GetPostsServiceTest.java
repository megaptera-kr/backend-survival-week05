package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
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
        //모의 객체 생성
        postRepository = mock(PostRepository.class);

        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        // given - 모의 객체에 대한 행동을 정의
        given(postRepository.findAll()).willReturn(List.of(
                Post.builder()
                        .id("1")
                        .title("title")
                        .content("content")
                        .author("author")
                        .build()
        ));
        // when - 테스트할 로직 실행
        List<PostDto> postDtos = getPostsService.getPostList();
        // then - 테스트 결과 검증
        assertThat(postDtos).hasSize(1);
    }
}
