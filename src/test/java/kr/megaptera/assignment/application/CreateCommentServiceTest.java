package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.CommentCreateDto;
import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateCommentServiceTest {
    private CommentRepository commentRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성 테스트")
    public void create_comment_test(){
        String postId = "001";
        // given
        CommentCreateDto commentCreateDto = new CommentCreateDto("a1", "b1");

        // when
        CommentDto result = createCommentService.createComment(postId, commentCreateDto);

        // then
        assertThat(result.getContent()).isEqualTo("b1");
        assertThat(result.getAuthor()).isEqualTo("a1");
    }
}
