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

class DeletePostServiceTest {
    private PostRepository postRepository;
    private DeletePostService deletePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() throws PostNotFoundException {
        var postEntity = new PostEntity(
                "0001POST",
                "제목",
                "작성자",
                "내용");

        given(postRepository.find(postEntity.getId())).willReturn(postEntity);

        var postReadDto = deletePostService.execute(postEntity.getId());
        assertThat(postReadDto.getId()).isEqualTo(postEntity.getId());
        assertThat(postReadDto.getTitle()).isEqualTo(postEntity.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(postEntity.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(postEntity.getContent());
    }
}
