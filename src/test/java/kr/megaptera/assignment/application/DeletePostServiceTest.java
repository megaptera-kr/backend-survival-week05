package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {
    private PostRepository postRepository;

    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        PostId postId = new PostId("001POST");

        Post post =
                new Post(postId, "제목", "작성자", new MultilineText("내용"));

        given(postRepository.find(postId)).willReturn(post);

        deletePostService.deletePost(postId.toString());

        verify(postRepository).delete(any(PostId.class));
    }
}
