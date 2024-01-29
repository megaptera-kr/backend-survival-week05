package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateCommentServiceTest {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("댓글 업데이트 테스트")
    void update() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        when(postRepository.findById(post.id())).thenReturn(post);
        Comment comment = new Comment("댓쓴이", MultilineText.from("댓글내용"));
        when(commentRepository.findAll(post.id())).thenReturn(List.of(comment));
        Constructor<CommentDto> constructor = CommentDto.class.getDeclaredConstructor(String.class, String.class, String.class);
        constructor.setAccessible(true);
        CommentDto inputDto = constructor.newInstance(comment.id().toString(), comment.author(), "수정내용");
        // Act
        CommentDto outputCommentDto = updateCommentService.update(comment.id().toString(), post.id().toString(), inputDto);
        // Assert
        assertThat(outputCommentDto).isNotNull();
        assertThat(outputCommentDto.getId()).isEqualTo(inputDto.getId());
        assertThat(outputCommentDto.getAuthor()).isEqualTo(inputDto.getAuthor());
        assertThat(outputCommentDto.getContent()).isEqualTo(inputDto.getContent());
        verify(postRepository).findById(post.id());
        verify(commentRepository).findAll(post.id());
        verify(commentRepository).save(eq(post.id()), any(Comment.class));
    }

    @Test
    @DisplayName("댓글 업데이트 테스트 - 댓글이 없는 경우")
    void updateWhenNoComment() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        Post post = new Post("title", "author", MultilineText.from("content"));
        when(postRepository.findById(post.id())).thenReturn(post);
        Comment comment = new Comment("댓쓴이", MultilineText.from("댓글내용"));
        when(commentRepository.findAll(post.id())).thenReturn(List.of(comment));
        String nonExistCommentId = "non-exist-comment-id";
        String nonExistCommentAuthor = "non-exist-comment-author";
        Constructor<CommentDto> constructor = CommentDto.class.getDeclaredConstructor(String.class, String.class, String.class);
        constructor.setAccessible(true);
        CommentDto inputDto = constructor.newInstance(nonExistCommentId, nonExistCommentAuthor, "수정내용");
        // Act
        assertThrows(CommentNotFoundException.class, () -> updateCommentService.update(nonExistCommentId, post.id().toString(), inputDto));
    }

    @Test
    @DisplayName("댓글 업데이트 테스트 - 댓글이 없는 경우")
    void updateWhenNoPost() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Arrange
        String nonExistPostId = "non-exist-post-id";
        when(postRepository.findById(PostId.from(nonExistPostId))).thenReturn(null);
        String nonExistCommentId = "non-exist-comment-id";
        String nonExistCommentAuthor = "non-exist-comment-author";
        Constructor<CommentDto> constructor = CommentDto.class.getDeclaredConstructor(String.class, String.class, String.class);
        constructor.setAccessible(true);
        CommentDto inputDto = constructor.newInstance(nonExistCommentId, nonExistCommentAuthor, "수정내용");
        // Act
        assertThrows(PostNotFoundException.class, () -> updateCommentService.update("nonExistCommentId", nonExistPostId, inputDto));
    }

}
