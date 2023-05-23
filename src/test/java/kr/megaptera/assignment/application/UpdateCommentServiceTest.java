package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void update() {
        CommentId commentId = new CommentId("1-1");
        PostId postId = new PostId("1");
        MultiLineText multiLineText = new MultiLineText("댓글 내용");

        Comment comment = new Comment(commentId, postId, "작성자", multiLineText);

        given(commentRepository.find(commentId.toString())).willReturn(comment);

        CommentUpdateDto commentUpdatedDto
                = new CommentUpdateDto("변경된 댓글 내용");

        updateCommentService.update(
                commentId.toString(),
                postId.toString(),
                commentUpdatedDto
        );

        assertThat(comment.content()).isEqualTo(MultiLineText.of("변경된 댓글 내용"));
    }
}
