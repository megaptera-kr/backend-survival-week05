package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import kr.megaptera.assignment.dtos.GetCommentListResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(GetCommentsService.class)
class GetCommentsServiceTest {

    private GetCommentsService sut;
    @MockBean
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        sut = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("댓글 목록 조회")
    void list() {
        // given
        List<Comment> commentList = List.of(new Comment(1L, 1L, "Harry", "댓글1"),
                new Comment(2L, 1L, "Potter", "댓글2"));
        given(commentRepository.findByPostId(1L)).willReturn(commentList);

        // when
        List<GetCommentListResponse> comments = sut.getCommentList(1L);

        // then
        assertThat(comments.size()).isEqualTo(2);

        for (int i = 0; i < comments.size(); i++) {
            assertThat(comments.get(i).getId()).isEqualTo(commentList.get(i).getId().toString());
            assertThat(comments.get(i).getPostId()).isEqualTo(
                    commentList.get(i).getPostId().toString());
            assertThat(comments.get(i).getAuthor()).isEqualTo(commentList.get(i).getAuthor());
            assertThat(comments.get(i).getContent()).isEqualTo(commentList.get(i).getContent());
        }
    }
}
