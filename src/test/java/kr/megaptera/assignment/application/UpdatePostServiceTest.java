package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
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

class UpdatePostServiceTest {
    private PostRepository postRepository;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }
    @Test
    @DisplayName("UPDATE POSTS")
    void getComments(){
        String id = "POST_ID_001";
        PostUpdateDto postUpdateDto = new PostUpdateDto("제목","내용");

        Post post = new Post(
                PostId.of("POST_ID_001"),
                "TITLE_001",
                "AUTHOR_001",
                MultilineText.of("CONTENT_001"));
        given(postRepository.find(post.id())).willReturn(post);

        PostDto updated = updatePostService.updatePost(id,postUpdateDto);

        assertThat(updated.getId()).isEqualTo(post.id().toString());
        assertThat(updated.getAuthor()).isEqualTo(post.author());
        assertThat(updated.getTitle()).isEqualTo(postUpdateDto.getTitle());
        assertThat(updated.getContent()).isEqualTo(postUpdateDto.getContent());
    }
}
