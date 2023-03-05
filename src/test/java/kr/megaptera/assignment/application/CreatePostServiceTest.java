package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

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
    void creat () {
        PostCreateDto postCreateDto = new PostCreateDto("TITLE_001","AUTHOR_001", "CONTENT_001 : CREATE POST TESTING");
        PostDto created = createPostService.createPost(postCreateDto);

        assertThat(created.getAuthor()).isEqualTo("AUTHOR_001");
        assertThat(created.getContent()).isEqualTo("CONTENT_001 : CREATE POST TESTING");
        assertThat(created.getTitle()).isEqualTo("TITLE_001");
        assertThat(created.getId()).isNotEmpty();
    }
}
