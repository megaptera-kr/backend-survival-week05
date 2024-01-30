package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("Delete Existing Comment")
    void deleteExistingComment() {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        PostId postId = post.id();
        Comment comment = new Comment("author", MultilineText.from("content"));
        CommentId commentId = comment.id();
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findAll(postId)).thenReturn(List.of(comment));

        // Act
        CommentDto outputCommentDto = deleteCommentService.delete(commentId.toString(), postId.toString());

        // Assert
        assertThat(outputCommentDto).isNotNull();
        assertThat(outputCommentDto.getAuthor()).isEqualTo(comment.author());
        assertThat(outputCommentDto.getContent()).isEqualTo(comment.content().toString());
        verify(postRepository).findById(postId);
        verify(commentRepository).findAll(postId);
        verify(commentRepository).delete(postId, commentId);
    }

    @Test
    @DisplayName("Delete Comment When Post Not Exists")
    void deleteNonExistingComment() {
        // Arrange
        PostId postId = PostId.from("non-exist post id");
        when(postRepository.findById(postId)).thenReturn(null);

        // Act
        assertThrows(PostNotFoundException.class, () -> deleteCommentService.delete("any comment Id", postId.toString()));
    }

    @Test
    @DisplayName("Delete Comment When Comment Not Exists")
    void deleteNonExistingComment2() {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        PostId postId = post.id();
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(commentRepository.findAll(postId)).thenReturn(Collections.emptyList());

        // Act
        assertThrows(CommentNotFoundException.class, () -> deleteCommentService.delete("non existing comment id", postId.toString()));
    }
}
