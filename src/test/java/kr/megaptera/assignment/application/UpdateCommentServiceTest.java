package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
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

class UpdateCommentServiceTest {
    private CommentRepository commentRepository;

    private UpdateCommentService updateCommentService;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }
    @Test
    @DisplayName("UPDATE COMMENT")
    void getComments(){
        String id = "POST_ID_001";
        String postId = "POST_ID_001";
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto("댓글변경");
        Comment comment = new Comment(CommentId.of(id),PostId.of(postId),"AUTHOR_001","COMMENT_001");
        given(commentRepository.find(CommentId.of(id),PostId.of(postId))).willReturn(comment);

        CommentDto updated = updateCommentService.updateComment(id,postId,commentUpdateDto);

        assertThat(updated.getId()).isEqualTo(comment.id().toString());
        assertThat(updated.getAuthor()).isEqualTo(comment.author());
        assertThat(updated.getContent()).isEqualTo(commentUpdateDto.getContent());
    }
}
