package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {
    private UpdateCommentService updateCommentService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("업데이트 테스트")
    void 업데이트(){
        CommentId commentId = new CommentId("1");
        PostId postId = new PostId("1");
        Comment comment = new Comment(commentId,"author",
            "content",postId);
        CommentUpdateDto cud = new CommentUpdateDto("new content");
        comment.update(cud.getContent());
        assertThat(comment.content()).isEqualTo("new content");
    }
}
