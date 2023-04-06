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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class DeletePostServiceTest {
    private PostRepository postRepository;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void delete_post() {
        // given
        String postId = "001";
        Post post = new Post(PostId.of(postId), "a", "b", new MultiLineText("c"));
        given(postRepository.findById(PostId.of(postId))).willReturn(post);

        // when
        PostDto postDto = deletePostService.deletePost(postId);

        // then
        assertThat(postDto.getTitle()).isEqualTo("a");
        assertThat(postDto.getAuthor()).isEqualTo("b");
        assertThat(postDto.getContent()).isEqualTo("c");

        verify(postRepository).findById(any(PostId.class));
    }
}
