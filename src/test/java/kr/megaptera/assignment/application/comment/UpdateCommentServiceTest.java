package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class UpdateCommentServiceTest {

    private CommentRepository commentRepository;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        String commentId = "co_01";
        String postId = "po_01";

        given(commentRepository.find(CommentId.of(commentId), PostId.of(postId)))
                .willReturn(
                        new Comment(
                                CommentId.of(commentId),
                                PostId.of(postId),
                                "author",
                                "beforeComment"
                        )
                );

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("afterContent");

        CommentDto update = updateCommentService.update(commentId, postId, commentUpdateDto);

        assertThat(update.getContent()).isEqualTo("afterContent");
    }
}
