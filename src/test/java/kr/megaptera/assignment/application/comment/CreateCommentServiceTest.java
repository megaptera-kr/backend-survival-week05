package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class CreateCommentServiceTest {

    private CommentRepository commentRepository;

    private CreateCommentService createCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void createComment() {
        String postId = "post_01";

        CommentCreateDto commentCreateDto = new CommentCreateDto("author", "content");

        CommentDto commentDto = createCommentService.create(postId, commentCreateDto);

        assertThat(commentDto.getAuthor()).isEqualTo("author");
        assertThat(commentDto.getContent()).isEqualTo("content");

        verify(commentRepository).save(any(Comment.class));
    }
}
