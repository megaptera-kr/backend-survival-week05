package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class CreatePostServiceTest {

    private PostRepository postRepository;
    private CreatePostService createPostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);

        createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {

        PostCreateDto postCreateDto = new PostCreateDto(
                "title",
                "author",
                "content"
        );

        Post post = new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                PostContent.of(postCreateDto.getContent())
        );
        
        given(postRepository.save(postCreateDto)).willReturn(post);

        PostDto postDto = createPostService.create(postCreateDto);

        assertThat(postDto.getTitle().equals("title"));
        assertThat(postDto.getAuthor().equals("author"));
        assertThat(postDto.getContent().equals("content"));

        verify(postRepository).save(any(PostCreateDto.class));
    }
}
