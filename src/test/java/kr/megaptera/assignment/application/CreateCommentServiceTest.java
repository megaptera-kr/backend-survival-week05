package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentCreateDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("게시물에 댓글 생성")
    void createComment() {
        CommentCreateDto commentCreateDto = new CommentCreateDto("운영자", "감사합니다. 앞으로 자주 이용해 주세요.");
        createCommentService.createComment("1", commentCreateDto);

        verify(commentRepository).save(any(PostId.class), any(Comment.class));
    }

}
