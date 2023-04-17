package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
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
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() throws PostNotFoundException {
        var postEntity = new PostEntity(
                "0001POST",
                "제목",
                "작성자",
                "내용");

        given(postRepository.find(postEntity.getId())).willReturn(postEntity);

        var postReadDto = getPostService.execute(postEntity.getId());

        assertThat(postReadDto.getId()).isEqualTo(postEntity.getId());
        assertThat(postReadDto.getTitle()).isEqualTo(postEntity.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(postEntity.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(postEntity.getContent());
    }
}
