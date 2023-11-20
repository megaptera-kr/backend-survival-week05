package kr.megaptera.assignment.application;


import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostAuthor;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class DeletePostServiceTest {

    @SpyBean
    private PostRepository postRepository;

    @SpyBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("게시물 삭제")
    void delete(){
        // Given
        String postId = "1";
        Post post = new Post(PostId.of(postId), PostTitle.of("제목"),PostAuthor.of("작성자"),MultilineText.of("내용"));
        when(postRepository.find(any())).thenReturn(post);
        doNothing().when(postRepository).delete(any()); // postRepository.delete(any()) 호출 시 아무 동작도 수행하지 않도록

        // When
        PostDto result = deletePostService.deletePostDto(postId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(postId);

        verify(postRepository).delete(any(PostId.class));
        verify(deletePostService).deletePostDto(postId);
    }
}
