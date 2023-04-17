package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class UpdateCommentServiceTest {
    private CommentRepository commentRepository;
    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("코멘트 수정")
    void update() throws CommentNotFoundException {
        var commentEntity = new CommentEntity(
                "0001Comment",
                "0001POST",
                "작성자",
                "내용");

        given(commentRepository.find(commentEntity.getId())).willReturn(commentEntity);

        var commentUpdateDto = new CommentUpdateDto("업데이트 된 내용");

        var postReadDto = updateCommentService.execute(
                commentEntity.getId(),
                commentEntity.getPostId(),
                commentUpdateDto);

        assertThat(postReadDto.getId()).isEqualTo(commentEntity.getId());
        assertThat(postReadDto.getAuthor()).isEqualTo(commentEntity.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(commentUpdateDto.getContent());
    }
}
