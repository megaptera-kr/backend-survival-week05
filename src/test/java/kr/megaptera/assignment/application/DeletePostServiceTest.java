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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeletePostServiceTest {

    private PostRepository postRepository;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("Delete Existing Post")
    void deleteExistingPost() {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        when(postRepository.findById(post.id())).thenReturn(Optional.of(post));

        // Act
        PostDto outputPostDto = deletePostService.delete(post.id().toString());

        // Assert
        assertThat(outputPostDto).isNotNull();
        assertThat(outputPostDto.getTitle()).isEqualTo(post.title());
        assertThat(outputPostDto.getAuthor()).isEqualTo(post.author());
        assertThat(outputPostDto.getContent()).isEqualTo(post.content().toString());
        verify(postRepository).findById(post.id());
        verify(postRepository).delete(post.id());
    }

    @Test
    @DisplayName("Delete Non-Existing Post")
    void deleteNonExistingPost() {
        //Arrange
        String postId = "non-existing-post-id";
        when(postRepository.findById(PostId.from(postId))).thenReturn(Optional.empty());

        // Act
        assertThrows(PostNotFoundException.class, () -> deletePostService.delete(postId));
    }
}
