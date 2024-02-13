package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetPostsServiceTest {
    private PostRepository postRepository;
    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 0개일 때 목록 조회")
    void listWithNoPost() {
        // Arrange
        when(postRepository.findAll()).thenReturn(List.of());
        // Act
        List<PostDto> postDtoList = getPostsService.list();
        // Assert
        assertThat(postDtoList).isEmpty();
        verify(postRepository).findAll();
    }

    @Test
    @DisplayName("게시물 1개일 때 목록 조회")
    void listWithPost() {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        when(postRepository.findAll()).thenReturn(List.of(post));
        // Act
        List<PostDto> postDtoList = getPostsService.list();
        // Assert
        assertThat(postDtoList).isNotEmpty();
        assertThat(postDtoList.size()).isEqualTo(1);
        assertThat(postDtoList.get(0).getTitle()).isEqualTo(post.title());
        assertThat(postDtoList.get(0).getAuthor()).isEqualTo(post.author());
        assertThat(postDtoList.get(0).getContent()).isEqualTo(post.content().toString());
        verify(postRepository).findAll();
    }

    @Test
    @DisplayName("게시물 복수 이상일 때 목록 조회")
    void listWithPosts() {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        Post post2 = new Post("title2", "author2", MultilineText.from("content2"));
        when(postRepository.findAll()).thenReturn(List.of(post, post2));
        // Act
        List<PostDto> postDtoList = getPostsService.list();
        // Assert
        assertThat(postDtoList).isNotEmpty();
        assertThat(postDtoList.size()).isEqualTo(2);
        assertThat(postDtoList.get(0).getTitle()).isEqualTo(post.title());
        assertThat(postDtoList.get(0).getAuthor()).isEqualTo(post.author());
        assertThat(postDtoList.get(0).getContent()).isEqualTo(post.content().toString());
        assertThat(postDtoList.get(1).getTitle()).isEqualTo(post2.title());
        assertThat(postDtoList.get(1).getAuthor()).isEqualTo(post2.author());
        assertThat(postDtoList.get(1).getContent()).isEqualTo(post2.content().toString());
        verify(postRepository).findAll();
    }
}
