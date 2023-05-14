package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repository.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
    private CommentRepository commentRepository;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 작성")
    void create() {
        String postId = "001POST";

        CommentDto newComment =
                new CommentDto("작성자", "댓글 내용");

        CommentDto commentDto =
                createCommentService.create(postId, newComment);

        assertThat(commentDto.getAuthor()).isEqualTo("작성자");
        assertThat(commentDto.getContent()).isEqualTo("댓글 내용");

        verify(commentRepository).save(any(Comment.class));
    }
}
