package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CreateCommentServiceTest {
    private CommentRepository commentRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("createCommentService Test")
    void 글생성(){
        CommentCreateDto ccd = new CommentCreateDto("나","내용");
        createCommentService.createCommentDto("1",ccd);

        assertThat(ccd.getAuthor()).isEqualTo("나");
    }
}
