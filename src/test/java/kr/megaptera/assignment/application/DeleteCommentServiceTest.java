package kr.megaptera.assignment.application;

import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        CommentId commentId = new CommentId("001COMMENT");

        PostId postId = new PostId("001POST");

        Comment comment = new Comment(commentId, postId, "작성자", "댓글 내용");

        given(commentRepository.find(commentId, postId)).willReturn(comment);

        deleteCommentService.deleteComment(
                commentId.toString(),
                postId.toString()
        );

        verify(commentRepository).delete(any(CommentId.class));
    }
}
