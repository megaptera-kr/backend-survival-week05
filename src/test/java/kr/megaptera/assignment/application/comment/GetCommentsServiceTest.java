package kr.megaptera.assignment.application.comment;

import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.model.post.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class GetCommentsServiceTest {

    private CommentRepository commentRepository;
    private GetCommentsService getCommentsService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("댓글 얻기")
    void getComment() {
        String postId = "post_01";

        given(commentRepository.findAll(postId))
                .willReturn(List.of(
                        new Comment(CommentId.of("Comment_01"), PostId.of("ID_01"), "author", "content"),
                        new Comment(CommentId.of("Comment_02"), PostId.of("ID_02"), "author", "content"))
                );

        List<CommentDto> commentDtos = getCommentsService.getCommentDtos(postId);

        assertThat(commentDtos).hasSize(2);
    }
}
