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


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class UpdatePostServiceTest {
    private UpdatePostService updatePostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }
    @Test
    @DisplayName("게시물 수정")
    void update() {
        PostId postId = new PostId("1");
        Post post = new Post(postId, new PostTitle("수정 전 제목"), "작성자", new PostTextContent("수정 전 내용"));
        given(postRepository.getPost(postId)).willReturn(post);

        PostDto update = new PostDto("수정 후", "수정내용");

        updatePostService.updatePost(postId.toString(), update);

        assertThat(post.title().toString()).isEqualTo("수정 후");
    }
}
