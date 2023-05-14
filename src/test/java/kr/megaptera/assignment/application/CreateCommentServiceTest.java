package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CreateCommentDto;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
    private CreateCommentService createCommentService;

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);

        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        CreateCommentDto createCommentDto = new CreateCommentDto("댓글 작성자", "댓글 내용");
        createCommentService.create("1", createCommentDto);
        verify(commentRepository).save(any());
    }
}
