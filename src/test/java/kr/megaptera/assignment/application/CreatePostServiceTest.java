package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.PostCreateDto;
import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreatePostServiceTest {
    private PostRepository postRepository;
    private CreatePostService createPostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        // given
        PostCreateDto postCreateDto = new PostCreateDto("a1", "b1", "c1");

        // when
        PostDto post = createPostService.createPost(postCreateDto);

        // then
        assertThat(post.getTitle()).isEqualTo("a1");
        assertThat(post.getContent()).isEqualTo("b1");
        assertThat(post.getAuthor()).isEqualTo("c1");
    }
}
