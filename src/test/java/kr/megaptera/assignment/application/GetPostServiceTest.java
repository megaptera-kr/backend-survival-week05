package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {

    private PostRepository postRepository;

    private GetPostService getPostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }
    @Test
    @DisplayName("GET POST")
    void getPost(){
        String id = "POST_ID_001";
        Post post = new Post(
                PostId.of("POST_ID_001"),
                "TITLE_001",
                "AUTHOR_001",
                MultilineText.of("CONTENT_001"));
        given(postRepository.find(post.id())).willReturn(post);

        PostDto postDto = getPostService.getPostDto(id);

        assertThat(postDto.getId()).isEqualTo(post.id().toString());
        assertThat(postDto.getTitle()).isEqualTo("TITLE_001");
        assertThat(postDto.getAuthor()).isEqualTo("AUTHOR_001");
        assertThat(postDto.getContent()).isEqualTo("CONTENT_001");
    }
}
