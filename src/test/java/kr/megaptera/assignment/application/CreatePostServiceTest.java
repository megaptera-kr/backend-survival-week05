package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

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
        PostDto postDto = createPostService.addPostDto(
                new PostDto(new Post("title1", "author1", "content1")));
        assertThat(postDto).isNotNull();
    }
}
