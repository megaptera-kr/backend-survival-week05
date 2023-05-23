package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        PostCreateDto testPost = new PostCreateDto("제목","글쓴이","내용");

        PostDto post = createPostService.createPost(testPost);
        assertThat(post.getTitle()).isEqualTo("제목");
        assertThat(post.getAuthor()).isEqualTo("글쓴이");
        assertThat(post.getContent()).isEqualTo("내용");

        verify(postRepository).save(any(Post.class));

    }
}
