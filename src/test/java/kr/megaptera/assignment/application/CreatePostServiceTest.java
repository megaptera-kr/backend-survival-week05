package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CreatePostDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {
    private CreatePostService createPostService;

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        CreatePostDto createPostDto = new CreatePostDto("제목", "저자", "내용");
        createPostService.createPost(createPostDto);
        verify(postRepository).save(any());
    }
}
