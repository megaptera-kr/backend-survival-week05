package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdatePostServiceTest {
    private PostRepository postRepository;
    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        when(postRepository.findById(post.id())).thenReturn(Optional.of(post));
        Post updatedPost = new Post(post, "수정된 제목", MultilineText.from("수정된 내용"));
        // Act
        PostDto outputPostDto = updatePostService.update(post.id().toString(), PostDto.from(updatedPost));
        // Assert
        assertThat(outputPostDto).isNotNull();
        assertThat(outputPostDto.getId()).isEqualTo(post.id().toString());
        assertThat(outputPostDto.getId()).isEqualTo(updatedPost.id().toString());
        assertThat(outputPostDto.getTitle()).isEqualTo(updatedPost.title());
        assertThat(outputPostDto.getAuthor()).isEqualTo(post.author());
        assertThat(outputPostDto.getAuthor()).isEqualTo(updatedPost.author());
        assertThat(outputPostDto.getContent()).isEqualTo(updatedPost.content().toString());
        verify(postRepository).findById(post.id());
        verify(postRepository).save(any(Post.class));
    }

    @Test
    @DisplayName("게시물 수정 - 게시물 없음")
    void updateWithNoPost() {
        // Arrange
        String nonExistingPostId = "non-existing-post-id";
        when(postRepository.findById(PostId.from(nonExistingPostId))).thenReturn(Optional.empty());
        PostDto mockDto = mock(PostDto.class);
        // Act
        assertThrows(PostNotFoundException.class, () -> updatePostService.update(nonExistingPostId, mockDto));
    }
}
