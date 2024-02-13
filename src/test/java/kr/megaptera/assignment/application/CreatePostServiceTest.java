package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {

    private PostRepository postRepository;
    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        createPostService = new CreatePostService(postRepository);
    }


    @Test
    @DisplayName("Create Post")
    void create() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Setup
        Constructor<PostDto> constructor = PostDto.class.getDeclaredConstructor(String.class, String.class, String.class, String.class);
        constructor.setAccessible(true);
        PostDto inputPostDto = constructor.newInstance(null, "title", "author", "content");

        // Act
        PostDto outputPostDto = createPostService.create(inputPostDto);

        // Assert
        assertThat(outputPostDto).isNotNull();
        assertThat(outputPostDto.getTitle()).isEqualTo(inputPostDto.getTitle());
        assertThat(outputPostDto.getAuthor()).isEqualTo(inputPostDto.getAuthor());
        assertThat(outputPostDto.getContent()).isEqualTo(inputPostDto.getContent());
        verify(postRepository).save(any(Post.class));
    }
}
