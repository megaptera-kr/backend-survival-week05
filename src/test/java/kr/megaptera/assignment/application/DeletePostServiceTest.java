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

class DeletePostServiceTest {

    private PostRepository postRepository;

    private DeletePostService deletePostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }
    @Test
    @DisplayName("DELETE POST")
    void deleteComment(){
        String id = "POST_ID_001";
        Post post = new Post(
                PostId.of("POST_ID_001"),
                "TITLE_001",
                "AUTHOR_001",
                MultilineText.of("CONTENT_001"));
        given(postRepository.find(post.id())).willReturn(post);

        PostDto deleted = deletePostService.deletePost(id);

        assertThat(deleted.getId()).isEqualTo(post.id().toString());
        assertThat(deleted.getTitle()).isEqualTo("TITLE_001");
        assertThat(deleted.getAuthor()).isEqualTo("AUTHOR_001");
        assertThat(deleted.getContent()).isEqualTo("CONTENT_001");
    }
}
