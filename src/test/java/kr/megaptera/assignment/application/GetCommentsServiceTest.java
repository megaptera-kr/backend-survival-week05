package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {
    private GetCommentsService getCommentsService;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        getCommentsService = new GetCommentsService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("댓글 조회")
    void get() {
        // given
        given(commentRepository.findAll(PostId.of("0001POST")))
                .willReturn(List.of(new Comment(
                        new CommentId("0001COMMENT"),
                        new PostId("0001POST"),
                        "작성자1",
                        "내용1")
                        ));

        // when
        List<CommentDto> comments = getCommentsService.list("0001POST");

        // then
        assertThat(comments).hasSize(1);
    }
}
