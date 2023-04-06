package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class GetPostServiceTest {

    private PostRepository postRepository;
    private GetPostService getPostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() {
        String postId = "po_001";

        given(postRepository.find(postId)).willReturn(new Post(
                new PostId(postId),
                "title",
                "author",
                new PostContent("content")
        ));

        PostDto postDto = getPostService.getPostDto(postId);

        assertThat(postDto.getId().equals(postId));
        assertThat(postDto.getTitle().equals("title"));
        assertThat(postDto.getAuthor().equals("author"));
        assertThat(postDto.getContent().equals("content"));
    }
}
