package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
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
        //모의 객체 생성
        postRepository = mock(PostRepository.class);

         createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        //given
        Post post = Post.builder()
                .id("1")
                .title("title")
                .content("content")
                .author("author")
                .build();

        //when
        PostDto postDto = createPostService.createPost(new PostDto(post));

        //then
        assertThat(postDto.getTitle()).isEqualTo("title");
        assertThat(postDto.getContent()).isEqualTo("content");
        assertThat(postDto.getAuthor()).isEqualTo("author");
    }
}
