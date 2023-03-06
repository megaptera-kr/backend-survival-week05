package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class GetPostServiceTest {
    private PostRepository postRepository;
    private GetPostService getPostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void getpost() {
        // given
        String id = "001";
        given(postRepository.findById(PostId.of(id))).willReturn(
                new Post(PostId.of(id), "a", "b", new MultiLineText("c"))
        );

        // when
        PostDto postDto = getPostService.findById(id);

        // then
        assertThat(postDto.getTitle()).isEqualTo("a");
        assertThat(postDto.getAuthor()).isEqualTo("b");
        assertThat(postDto.getContent()).isEqualTo("c");

        verify(postRepository, times(1)).findById(any(PostId.class));
    }
}
