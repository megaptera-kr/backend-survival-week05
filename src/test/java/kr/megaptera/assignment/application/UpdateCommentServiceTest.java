package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {
    CommentRepository commentRepository;

    UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() throws CommentNotFound {
        CommentId commentId = new CommentId("001COMMENT");
        PostId postId = new PostId("001POST");

        Comment comment = new Comment(commentId, postId, "작성자", "댓글 내용");

        given(commentRepository.find(commentId, postId)).willReturn(comment);

        CommentDto commentUpdatedDto
                = new CommentDto("변경된 댓글 내용");

        updateCommentService.updateComment(
                commentId.toString(),
                postId.toString(),
                commentUpdatedDto
        );

        assertThat(comment.content()).isEqualTo("변경된 댓글 내용");
    }
}
