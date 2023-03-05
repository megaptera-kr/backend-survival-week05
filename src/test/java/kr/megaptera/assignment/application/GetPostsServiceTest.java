package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostsServiceTest {
    private PostRepository postRepository;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        getPostsService = new GetPostsService(postRepository);
    }
    @Test
    @DisplayName("GET POSTS")
    void getComments(){
        String id = "POST_ID_001";

        List<Post> posts = Arrays.asList(
                new Post(PostId.of(id),"TITLE_001", "AUTHOR_001", MultilineText.of("CONTENT_001")),
                new Post(PostId.of(id),"TITLE_002", "AUTHOR_002", MultilineText.of("CONTENT_002")),
                new Post(PostId.of(id),"TITLE_003", "AUTHOR_003", MultilineText.of("CONTENT_003"))
        );

        given(postRepository.findAll()).willReturn(posts);

        List<PostDto> created = getPostsService.getPostDtos();

        assertThat(created).hasSize(3);
    }
}
