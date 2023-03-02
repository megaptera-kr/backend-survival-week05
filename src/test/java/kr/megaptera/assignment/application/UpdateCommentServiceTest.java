package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import kr.megaptera.assignment.dtos.UpdateCommentRequest;
import kr.megaptera.assignment.dtos.UpdateCommentResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(UpdateCommentService.class)
class UpdateCommentServiceTest {

    private UpdateCommentService sut;

    @MockBean
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        sut = new UpdateCommentService(commentRepository);
    }

    @DisplayName("댓글 수정")
    @Test
    void update() {
        // given
        UpdateCommentRequest request = new UpdateCommentRequest("댓글바뀜");
        Comment oldComment = new Comment(1L, 1L, "Harry", "댓글1");
        given(commentRepository.findById(1L)).willReturn(oldComment);

        // when
        UpdateCommentResponse comment = sut.updateComment(1L, 1L, request);

        // then
        assertThat(comment.getId()).isEqualTo(oldComment.getId().toString());
        assertThat(comment.getPostId()).isEqualTo(oldComment.getPostId().toString());
        assertThat(comment.getAuthor()).isEqualTo(oldComment.getAuthor());
        assertThat(comment.getContent()).isEqualTo(request.getContent());
    }
}
