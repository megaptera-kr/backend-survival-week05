package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentContent;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {
    private DeleteCommentService deleteCommentService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete(){
        CommentId commentId = new CommentId("1");
        Comment comment = new Comment(commentId, "작성자", new CommentContent("삭제될 댓글"));

        given(commentRepository.findComment(commentId)).willReturn(comment);

        commentRepository.delete(commentId);
        verify(commentRepository).delete(any(CommentId.class));
    }
}
