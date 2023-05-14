package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.dto.CommentUpdateDto;
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

class UpdateCommentServiceTest {

    private CommentRepository commentRepository;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("게시물에 이미 존재하는 댓글 수정")
    void updateComment() {
        given(commentRepository.find(CommentId.of("3"), PostId.of("1")))
                .willReturn(
                        new Comment(
                                CommentId.of("3"),
                                PostId.of("1"),
                                CommentAuthor.of("케이"),
                                MultilineText.of("감사합니다!\n운영자님")
                        )
                );

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("아차, 수정하겠습니다.\n또 만나요~");
        CommentDto commentDto = updateCommentService.updateComment("3", "1", commentUpdateDto);

        verify(commentRepository).save(any(PostId.class), any(Comment.class));
        assertThat(commentDto.getContent()).contains("수정하겠습니다");
        assertThrows(CommentNotFound.class, () -> updateCommentService.updateComment("4", "1", commentUpdateDto));
        assertThrows(CommentNotFound.class, () -> updateCommentService.updateComment("3", "10", commentUpdateDto));
    }

}
