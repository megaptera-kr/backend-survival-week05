package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {
    private PostRepository postRepository;
    private UpdatePostService updatePostService;
    @BeforeEach
    void setUp(){
        postRepository=mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }
    @Test
    @DisplayName("게시물 수정")
    void update() {
        Post post = new Post(PostId.of("1"),"title","author","content");
        given(postRepository.find(PostId.of("1"))).willReturn(post);
        PostUpdateDto pud = new PostUpdateDto("newtitle","newcontent");
        updatePostService.update("1",pud);
        assertThat(post.getTitle()).isEqualTo("newtitle");
    }
}
