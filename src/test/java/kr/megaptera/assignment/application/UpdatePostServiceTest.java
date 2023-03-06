package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.controllers.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.after;
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
    @DisplayName("게시글 수정 테스트")
    void update_test() throws  Exception{
        // given
        String postId = "001";
        PostUpdateDto postUpdateDto = new PostUpdateDto("a1", "c1");
        Post post = new Post("a", "b", new MultiLineText("c"));
        given(postRepository.findById(PostId.of(postId))).willReturn(post);

        // when
        PostDto postDto = updatePostService.updatePost(postId, postUpdateDto);

        // then
        assertThat(postDto.getTitle()).isEqualTo("a1");
        assertThat(postDto.getContent().toString()).isEqualTo("c1");
    }


}
