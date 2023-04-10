package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("Delete Comment")
    void 댓글삭제(){
        PostId postId = new PostId("1");
        CommentId commentId = new CommentId("1");
        Comment comment = new Comment(commentId,"author","content",postId);
        given(commentRepository.find(commentId, postId))
                .willReturn(comment);
        deleteCommentService.deleteComment("1","1");

        verify(commentRepository).delete(any(CommentId.class));
    }
}
