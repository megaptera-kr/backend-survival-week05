package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
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
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("임의의 게시물 하나에 대한 댓글 목록 조회")
    void list() {
        String postId = "001";
        given(commentRepository.findByPostId(postId)).willReturn(List.of(
                new Comment(PostId.of("001"), "author1", "content1"),
                new Comment(PostId.of("001"), "author2", "content2")
        ));

        List<CommentDto> commentDtos = getCommentsService.getCommentDtos(postId);
        assertThat(commentDtos).hasSize(2);
    }

}
