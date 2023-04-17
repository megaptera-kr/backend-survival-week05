package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.*;
import org.junit.jupiter.api.*;

import java.util.*;

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
    @DisplayName("댓글 목록 조회")
    void list(){
        given(commentRepository.findAll(PostId.of("1")))
                .willReturn(List.of(new Comment
                        (new CommentId("1"),
                                "author",
                                "contnent",
                                new PostId("1"))));
        List<CommentDto> commentDtos = getCommentsService.getCommentDtos("1");
        assertThat(commentDtos).hasSize(1);
    }
}
