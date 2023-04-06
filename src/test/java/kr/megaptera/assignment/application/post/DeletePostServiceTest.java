package kr.megaptera.assignment.application.post;

import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.BDDMockito.*;

class DeletePostServiceTest {

    private PostRepository postRepository;
    private DeletePostService deletePostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        String id = "ID_01";
        PostId postId = new PostId(id);
        Post post = new Post(
                postId,
                "title",
                "author",
                new PostContent("content")
        );

        given(postRepository.find(postId.toString())).willReturn(post);

        deletePostService.delete(postId.toString());

        verify(postRepository).remove(any(Post.class));
        
    }
}
