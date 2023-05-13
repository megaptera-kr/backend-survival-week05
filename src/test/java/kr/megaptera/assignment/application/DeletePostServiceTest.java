package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTextContent;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {
    private DeletePostService deletePostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }
    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        PostId postId = new PostId("1");
        Post post = new Post(postId, new PostTitle("히히"), "작성자", new PostTextContent("내용이지롱"));

        given(postRepository.getPost(postId)).willReturn(post);
        postRepository.delete(postId);

        verify(postRepository).delete(postId);

    }
}
