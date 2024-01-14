package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdatePostServiceTest {

    private PostRepository postRepository;
    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        //모의 객체 생성
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }
    @Test
    @DisplayName("게시물 수정")
    void updatePost() throws PostNotFound {
        //given
        Post post = Post.builder()
                .id("1")
                .title("title")
                .content("content")
                .build();

        Post updatedPost = Post.builder()
                .title("updated title")
                .content("updated content")
                .build();

        given(postRepository.find(post.getId())).willReturn(post);

        // when
        updatePostService.updatePost(post.getId(), new PostDto(updatedPost));

        // then
        assertThat(post.getTitle()).isEqualTo(updatedPost.getTitle());
        assertThat(post.getContent()).isEqualTo(updatedPost.getContent());

    }
}
