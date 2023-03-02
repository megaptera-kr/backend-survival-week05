package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import kr.megaptera.assignment.dtos.DeleteCommentResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(DeleteCommentService.class)
class DeleteCommentServiceTest {
    private DeleteCommentService sut;

    @MockBean
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        sut = new DeleteCommentService(commentRepository);
    }

    @DisplayName("댓글 삭제")
    @Test
    void delete() {
        // given
        Comment oldComment = new Comment(1L, 1L, "Harry", "댓글1");
        given(commentRepository.delete(1L)).willReturn(oldComment);

        // when
        DeleteCommentResponse deleted = sut.deleteComment(1L, 1L);

        // then
        assertThat(deleted.getId()).isEqualTo(oldComment.getId().toString());
        assertThat(deleted.getPostId()).isEqualTo(oldComment.getPostId().toString());
        assertThat(deleted.getAuthor()).isEqualTo(oldComment.getAuthor());
        assertThat(deleted.getContent()).isEqualTo(oldComment.getContent());
    }
}
