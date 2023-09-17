package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void delete() {
        CommentId commentId = new CommentId("0001COMMENT");
        PostId postId = new PostId("0001POST");
        MultilineText commentContent = new MultilineText("내용");

        Comment comment = new Comment(commentId, postId, "작성자", commentContent);

        given(commentRepository.find(commentId, postId)).willReturn(comment);

        deleteCommentService.deleteComment(
                commentId.toString(),
                postId.toString()
        );

        verify(commentRepository).delete(any(CommentId.class));
    }
}
