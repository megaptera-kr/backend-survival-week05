package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetCommentsServiceTest {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        getCommentsService = new GetCommentsService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("존재하는 글의 댓글 목록 조회")
    void getCommentsOfExistingPost() {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        PostId postId = post.id();
        Comment comment = new Comment("댓쓴이", MultilineText.from("댓글내용"));
        when(postRepository.findById(postId)).thenReturn(post);
        when(commentRepository.findAll(postId)).thenReturn(List.of(comment));
        // Act
        List<CommentDto> output = getCommentsService.list(postId.toString());
        // Assert
        assertThat(output).isNotNull();
        assertThat(output).hasSize(1);
        assertThat(output.get(0).getAuthor()).isEqualTo(comment.author());
        assertThat(output.get(0).getContent()).isEqualTo(comment.content().toString());
        verify(postRepository).findById(postId);
        verify(commentRepository).findAll(postId);
    }

    @Test
    @DisplayName("존재하지 않는 글의 댓글 목록 조회")
    void getCommentsOfNonExistingPost() {
        // Arrange
        PostId postId = PostId.from("non-existing-post-id");
        when(postRepository.findById(postId)).thenReturn(null);
        // Act
        assertThrows(PostNotFoundException.class, () -> getCommentsService.list(postId.toString()));
    }
}
