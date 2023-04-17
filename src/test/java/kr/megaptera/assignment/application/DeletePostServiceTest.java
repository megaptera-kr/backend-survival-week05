package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {
    private PostRepository postRepository;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp(){
    postRepository = mock(PostRepository.class);
    deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        PostId postId = new PostId("1");
        Post post =
                new Post(postId,"title","author","content");
        given(postRepository.find(postId))
                .willReturn(post);
        deletePostService.delete("1");
        verify(postRepository).delete(any(PostId.class));
    }
}
