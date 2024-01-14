package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.utils.PostUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
    @DisplayName("댓글 삭제")
    void delete() throws CommentNotFound {
        //given
        String id = "1";
        String postId = "1";

        given(commentRepository.delete(id)).willReturn(true);

        //when
        deleteCommentService.deleteComment(id, postId);

        //then
        assertThat(commentRepository.delete(id)).isEqualTo(true);
    }

    @Test
    @DisplayName("댓글 삭제 실패")
    void deleteFail() throws CommentNotFound {
        //given
        Comment comment = Comment.builder()
                .id("1")
                .postId("1")
                .content("content")
                .author("author")
                .build();

        given(commentRepository.find(comment.getId(), comment.getPostId())).willReturn(comment);

        //when
        deleteCommentService.deleteComment(comment.getId(), comment.getPostId());

        //then
        assertThat(commentRepository.delete(comment.getId())).isEqualTo(false);
    }

    @Test
    @DisplayName("댓글 삭제 실패 - 댓글이 없는 경우")
    void deleteFailBecauseCommentNotFound() throws CommentNotFound {
        //given
        String id = "1";
        String postId = "1";

        given(commentRepository.find("2","1")).willReturn(null);

        //when
        deleteCommentService.deleteComment(id, postId);

        //then
        assertThat(commentRepository.delete(id)).isEqualTo(false);
    }


}
