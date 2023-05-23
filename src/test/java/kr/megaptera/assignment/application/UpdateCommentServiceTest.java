package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentContent;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateCommentServiceTest {
    private UpdateCommentService updateCommentService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 수정")
    void update() {
        CommentId commentId = new CommentId("1");
        Comment comment = new Comment(commentId, "작성자", new CommentContent("수정 전 내용"));

        given(commentRepository.findComment(commentId)).willReturn(comment);

        CommentDto update = new CommentDto("수정 후 내용");

        updateCommentService.update(commentId.toString(), update);

        assertThat(comment.Content().toString()).isEqualTo("수정 후 내용");

    }


}
