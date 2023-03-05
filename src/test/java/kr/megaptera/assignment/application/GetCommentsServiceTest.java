package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
    @DisplayName("GET COMMENTS")
    void getComments(){
        String postId = "POST_ID_001";

        List<Comment> comments = Arrays.asList(
                new Comment(CommentId.of("COMMENT_001"), PostId.of("POST_ID_001"), "AUTHOR_001", "CONTENT_001"),
                new Comment(CommentId.of("COMMENT_002"), PostId.of("POST_ID_001"), "AUTHOR_002", "CONTENT_002")
                );

        given(commentRepository.findAll(PostId.of(postId))).willReturn(comments);

        List<CommentDto> created = getCommentsService.getCommentDtos(postId);

        assertThat(created).hasSize(2);
    }
}
