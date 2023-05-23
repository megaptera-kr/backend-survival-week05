package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void delete() {
        CommentId commentId = new CommentId("1-1");
        PostId postId = new PostId("1");
        MultiLineText multiLineText = new MultiLineText("댓글 내용");

        Comment comment = new Comment(commentId, postId, "작성자", multiLineText);

        given(commentRepository.find(commentId.toString())).willReturn(comment);

        deleteCommentService.delete(
                commentId.toString(),
                postId.toString()
        );

        verify(commentRepository).delete(commentId.toString());
    }
}
