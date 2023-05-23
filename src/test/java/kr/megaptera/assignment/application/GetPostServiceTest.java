package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {
    private GetPostService getPostService;

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() {
        given(postRepository.findPost(any())).willReturn(new Post(new PostId("1"), "제목", "저자", "작성자"));

        PostDto postDto = getPostService.post("1");

        assertThat(postDto.getId()).isEqualTo("1");
        assertThat(postDto.getTitle()).isEqualTo("제목");
        assertThat(postDto.getAuthor()).isEqualTo("저자");
        assertThat(postDto.getContent()).isEqualTo("작성자");
    }
}
