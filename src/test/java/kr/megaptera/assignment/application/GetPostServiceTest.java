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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetPostServiceTest {

    private PostRepository postRepository;
    private GetPostService getPostService;


    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("존재하는 게시물 조회")
    void getExistingPost() {
        //  Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        PostId postId = post.id();
        when(postRepository.findById(postId)).thenReturn(post);
        //  Act
        PostDto postDto = getPostService.detail(postId.toString());
        //  Assert
        assertThat(postDto).isNotNull();
        assertThat(postDto.getTitle()).isEqualTo(post.title());
        assertThat(postDto.getAuthor()).isEqualTo(post.author());
        assertThat(postDto.getContent()).isEqualTo(post.content().toString());
        verify(postRepository).findById(postId);
    }

    @Test
    @DisplayName("존재하지 않는 게시물 조회")
    void getNonExistingPost() {
        // Arrange
        String postId = "non-existing-post-id";
        when(postRepository.findById(PostId.from(postId))).thenReturn(null);
        // Act
        assertThrows(PostNotFoundException.class, () -> getPostService.detail(postId));
    }
}
