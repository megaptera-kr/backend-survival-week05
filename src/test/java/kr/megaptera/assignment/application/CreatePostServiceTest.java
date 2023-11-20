package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostAuthor;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@SpringBootTest
class CreatePostServiceTest {

    @SpyBean
    private PostRepository postRepository;

    @SpyBean
    private CreatePostService createPostService;

    @Test
    @DisplayName("게시물 생성")
    void create() {
        PostDto newPostDto = new PostDto(new Post(PostTitle.of("제목"), PostAuthor.of("글쓴이"), MultilineText.of("내용")));

        PostDto createdPostDto = createPostService.createPostDto(newPostDto);

        Assertions.assertThat(createdPostDto).isEqualTo(newPostDto);

        verify(postRepository).save(any(Post.class));
        verify(createPostService).createPostDto(newPostDto);
    }
}
