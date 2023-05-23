package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.Test;

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
        Post post = new Post("1","제목","글쓴이","글내용");

        given(postRepository.find(post.id())).willReturn(post);

        deletePostService.deletePost(post.id());

        verify(postRepository).delete(post.id());
    }
}
