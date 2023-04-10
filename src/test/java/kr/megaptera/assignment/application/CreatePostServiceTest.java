package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreatePostServiceTest {

    private PostRepository postRepository;
    private CreatePostService createPostService;

    @BeforeEach
    void setUp(){
    postRepository = mock(PostRepository.class);
    createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        PostCreateDto postCreateDto = new PostCreateDto("제목","작성자","내용");
        PostDto postDto = createPostService.createPost(postCreateDto);

        assertThat(postDto.getTitle()).isEqualTo("제목");
    }
}
