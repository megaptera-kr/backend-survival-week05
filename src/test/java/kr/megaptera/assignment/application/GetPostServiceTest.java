package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTextContent;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {
    private GetPostService getPostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }
    @Test
    @DisplayName("게시물 조회")
    void detail() {
        PostId postId = new PostId("1");
        given(postRepository.getPost(postId)).willReturn(new Post(
                postId, new PostTitle("푸하하"), "작성자", new PostTextContent("내용이곧제목")));

        Post post = postRepository.getPost(postId);

        assertThat(post.id()).isEqualTo(postId);
        assertThat(post.title().toString()).isEqualTo("푸하하");
    }
}
