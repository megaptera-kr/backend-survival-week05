package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostCreateDto;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {

    private PostRepository postRepository;

    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        PostCreateDto postCreateDto = new PostCreateDto("새로 글 쓰기", "케이", "새로 글을\n쓰는 중입니다.\n\n좋아용");
        createPostService.createPost(postCreateDto);

        verify(postRepository).save(any(Post.class));
    }
}
