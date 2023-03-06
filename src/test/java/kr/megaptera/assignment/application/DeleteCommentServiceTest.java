package kr.megaptera.assignment.application;


import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
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
    @DisplayName("게시물 삭제")
    void delete() {
        String postId = "001";
        String commentId = "0001";
        given(commentRepository.find(CommentId.of(commentId)))
                .willReturn(new Comment(new CommentId(commentId), PostId.of(postId), "author1", "content1"));

        CommentDto removedCommentDto = deleteCommentService.removeCommentDto(commentId);
        assertThat(removedCommentDto).isNotNull();
    }

}
