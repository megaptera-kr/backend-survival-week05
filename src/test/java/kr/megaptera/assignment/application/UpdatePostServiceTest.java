package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        // given
        given(postRepository.find(PostId.of("0001POST")))
                .willReturn(Optional.of(new Post(
                                new PostId("0001POST"),
                                "제목1",
                                "작성자1",
                                new MultilineText("내용")
                        ))
                );

        // when
        PostDto updated = updatePostService.update("0001POST", new PostUpdateDto("제목2", "내용2"));

        // then
        assertThat(updated).isNotNull();
        assertThat(updated.getTitle()).isEqualTo("제목2");
        assertThat(updated.getContent()).isEqualTo("내용2");
    }
}
