package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {
    private CommentRepository commentRepository;
    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    public void update_comment_test(){
        // given
        String commentId = "0001";
        String postId = "001";
        String content = "updated";
        Comment comment = new Comment(PostId.of("001"), "a1", "b1");
        given(commentRepository.find(CommentId.of(commentId))).willReturn(comment);

        // when
        CommentDto commentDto = updateCommentService.updateComment(commentId, postId, content);

        // then
        assertThat(commentDto.getContent()).isEqualTo("updated");
    }
}
