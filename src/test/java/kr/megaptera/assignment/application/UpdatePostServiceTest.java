package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdatePostServiceTest {

    private PostRepository postRepository;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }
    @Test
    @DisplayName("게시물 수정")
    void update() {
        given(postRepository.find(PostId.of("001")))
                .willReturn(new Post(new PostId("001"), "title1", "author1", "content1"));

        PostDto updatedPostDto = updatePostService.updatePostDto("001",
                new PostDto(new Post(new PostId("001"), "title2", "author1", "content2")));
        assertThat(updatedPostDto).isNotNull();
    }
}
