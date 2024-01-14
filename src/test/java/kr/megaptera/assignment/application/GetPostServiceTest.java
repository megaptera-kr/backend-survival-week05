package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
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
    void detail() throws PostNotFound {
        //given
        given(postRepository.find("1")).willReturn(
                Post.builder()
                        .id("1")
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());

        //when
        PostDto postDto = getPostService.getPost("1");

        //then
        assertThat(postDto.getContent()).isEqualTo("content");
        assertThat(postDto.getAuthor()).isEqualTo("author");
        assertThat(postDto.getTitle()).isEqualTo("title");
    }
}
