package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("코멘트 삭제")
    void delete() throws CommentNotFoundException {
        var commentEntity = new CommentEntity(
                "0001Comment",
                "0001POST",
                "작성자",
                "내용");

        given(commentRepository.find(commentEntity.getId())).willReturn(commentEntity);

        var commentReadDto = deleteCommentService.execute(commentEntity.getId());
        assertThat(commentReadDto.getId()).isEqualTo(commentEntity.getId());

        assertThat(commentReadDto.getAuthor()).isEqualTo(commentEntity.getAuthor());
        assertThat(commentReadDto.getContent()).isEqualTo(commentEntity.getContent());
    }

}
