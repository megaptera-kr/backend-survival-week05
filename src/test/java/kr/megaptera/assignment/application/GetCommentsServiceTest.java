package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {
    private GetCommentsService getCommentsService;

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("댓글 조회")
    void list() {
        given(commentRepository.findAll(any())).willReturn(List.of(new Comment(
            CommentId.of("2"),
            PostId.of("1"),
            "댓글 작성자",
            "댓글 내용")
        ));

        List<CommentDto> commentDtos = getCommentsService.comments("1");

        assertThat(commentDtos).hasSize(1);
    }
}
