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

class DeletePostServiceTest {

    private PostRepository postRepository;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        //모의 객체 생성
        postRepository = mock(PostRepository.class);

        deletePostService = new DeletePostService(postRepository);
    }
    @Test
    @DisplayName("게시물 삭제")
    void delete() throws PostNotFound {
        //given
        Post post = Post.builder()
                .id("1")
                .title("title")
                .content("content")
                .author("author")
                .build();

        given(postRepository.find(post.getId())).willReturn(post);

        //when
        deletePostService.deletePost(post.getId());
        //then
        assertThat(postRepository.delete(post.getId())).isEqualTo(true);
    }
}
