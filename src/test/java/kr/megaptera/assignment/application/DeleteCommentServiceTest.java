package kr.megaptera.assignment.application;

import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCommentServiceTest {

    private CommentRepository commentRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() throws CommentNotFound {
        //given
        String id = "1";
        String postId = "1";

        given(commentRepository.delete(id)).willReturn(true);

        //when
        deleteCommentService.deleteComment(id, postId);

        //then
        assertThat(commentRepository.delete(id)).isEqualTo(true);
    }
}
