package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        PostId postId = new PostId("1");

        Post post =
                new Post(postId, new PostTitle("제목"), "작성자", new MultiLineText("내용"));

        given(postRepository.find(postId.toString())).willReturn(post);

        deletePostService.delete(postId.toString());

        verify(postRepository).delete(postId.toString());
    }
}
