package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.mockito.BDDMockito.*;

class DeleteCommentServiceTest {

    private CommentRepository commentRepository;

    private DeleteCommentService deleteCommentService;


    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() {
        String commentId = "co_01";
        String postId = "po_01";

        given(commentRepository.find(CommentId.of(commentId), PostId.of(postId)))
                .willReturn(new Comment(
                        PostId.of(postId),
                        "author",
                        "content")
                );

        deleteCommentService.delete(commentId, postId);

        verify(commentRepository).remove(any(Comment.class));
    }
}
