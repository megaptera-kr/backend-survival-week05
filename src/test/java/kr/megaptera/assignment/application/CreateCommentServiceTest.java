package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateCommentServiceTest {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        createCommentService = new CreateCommentService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("Create Comment")
    void create() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Setup
        Post post = new Post("title", "author", MultilineText.from("content"));
        PostId postId = post.id();
        postRepository.save(post);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        Constructor<CommentDto> constructor = CommentDto.class.getDeclaredConstructor(String.class, String.class, String.class);
        constructor.setAccessible(true);
        CommentDto inputCommentDto = constructor.newInstance(null, "commentAuthor", "commentContent");

        // Act
        CommentDto outputCommentDto = createCommentService.create(postId.toString(), inputCommentDto);

        // Assert
        assertThat(outputCommentDto).isNotNull();
        assertThat(outputCommentDto.getAuthor()).isEqualTo(inputCommentDto.getAuthor());
        assertThat(outputCommentDto.getContent()).isEqualTo(inputCommentDto.getContent());
        verify(commentRepository).save(eq(postId), any(Comment.class));
    }

}
