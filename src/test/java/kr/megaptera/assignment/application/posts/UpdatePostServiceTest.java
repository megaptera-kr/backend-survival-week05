package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostUpdateDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
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
    void update() throws PostNotFoundException {
        var postEntity = new PostEntity(
                "0001POST",
                "제목",
                "작성자",
                "내용");

        given(postRepository.find(postEntity.getId())).willReturn(postEntity);

        var id = postEntity.getId();
        var postUpdateDto = new PostUpdateDto("업데이트 된 제목", "업데이트 된 내용");

        var postReadDto = updatePostService.execute(id, postUpdateDto);

        assertThat(postReadDto.getId()).isEqualTo(postEntity.getId());
        assertThat(postReadDto.getTitle()).isEqualTo(postUpdateDto.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(postEntity.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(postUpdateDto.getContent());
    }
}
