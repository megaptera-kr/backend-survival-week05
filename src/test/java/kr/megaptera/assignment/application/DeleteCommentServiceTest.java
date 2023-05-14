package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.CommentAuthor;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.domains.model.MultilineText;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {

    private CommentRepository commentRepository;

    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("게시물에 이미 존재하는 댓글 삭제")
    void delete() {
        given(commentRepository.find(CommentId.of("3"), PostId.of("1")))
                .willReturn(
                        new Comment(
                                CommentId.of("3"),
                                PostId.of("1"),
                                CommentAuthor.of("케이"),
                                MultilineText.of("감사합니다!\n운영자님")
                        )
                );

        CommentDto commentDto = deleteCommentService.deleteComment("3", "1");

        verify(commentRepository).delete(any(CommentId.class), any(PostId.class));

        assertThat(commentDto.getContent()).contains("운영자님");
        assertThrows(CommentNotFound.class, () -> deleteCommentService.deleteComment("4", "1"));
        assertThrows(CommentNotFound.class, () -> deleteCommentService.deleteComment("3", "10"));
    }

}
