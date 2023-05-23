package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.UpdateCommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdateCommentServiceTest {
    private UpdateCommentService updateCommentService;

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        Comment comment = new Comment(CommentId.of("1"), PostId.of("1"), "댓글 작성자", "댓글 내용");
        UpdateCommentDto updateCommentDto = new UpdateCommentDto("댓글 수정 내용");
        given(commentRepository.findComment(any())).willReturn(comment);

        assertThat(comment.content()).isEqualTo("댓글 내용");

        updateCommentService.update(comment.commentId().toString(), comment.postId().toString(), updateCommentDto);

        assertThat(comment.content()).isEqualTo("댓글 수정 내용");
    }
}
