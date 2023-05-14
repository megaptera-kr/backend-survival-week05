package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {
    private CommentRepository commentRepository;
    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("댓글 목록 조회")
    void list() {
        PostId postId = new PostId("001POST");

        given(commentRepository.findAll(postId)).willReturn(List.of(
                new Comment(
                        new CommentId("001COMMENT"),
                        postId,
                        "작성자",
                        "댓글 내용"
                )
        ));

        List<CommentDto> commentDtos
                = getCommentsService.getCommentDtos(postId.toString());

        assertThat(commentDtos).hasSize(1);
    }
}
