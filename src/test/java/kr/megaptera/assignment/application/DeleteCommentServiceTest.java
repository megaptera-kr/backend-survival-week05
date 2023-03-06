package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    public void delete_comment_test(){
        // given
        String commentId = "0001";
        String postId = "001";
        Comment comment = new Comment(CommentId.of(commentId), PostId.of(postId), "a1", "c1");
        given(commentRepository.find(CommentId.of(commentId))).willReturn(comment);

        // when
        CommentDto commentDto = deleteCommentService.deleteComment(commentId, postId);

        // then
        assertThat(commentDto.getAuthor()).isEqualTo("a1");
        assertThat(commentDto.getContent()).isEqualTo("c1");
    }
}
