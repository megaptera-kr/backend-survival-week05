package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class GetPostServiceTest {

    private PostRepository postRepository;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        getPostService = new GetPostService(postRepository);
    }
    @Test
    @DisplayName("게시물 조회")
    void detail() {
        Post post = new Post("1","제목","글쓴이","내용");
    }
}
