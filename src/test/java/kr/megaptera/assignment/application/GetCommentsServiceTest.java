package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {
    private CommentRepository commentRepository;
    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("댓글 목록 테스트")
    public void list_comment_test(){

        // given
        String postId = "001";
        List<Comment> comments1 = List.of(
                new Comment(PostId.of(postId), "a", "b")
        );
        given(commentRepository.getComments(PostId.of(postId))).willReturn(comments1);

        // when
        List<CommentDto> comments = getCommentsService.getComments(postId);

        // then
        assertThat(comments).hasSize(1);
        assertThat(comments.get(0).getContent()).isEqualTo("b");
    }
}
