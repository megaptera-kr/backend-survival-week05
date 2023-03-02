package kr.megaptera.assignment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import kr.megaptera.assignment.dtos.CreateCommentResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CreateCommentService.class)
class CreateCommentServiceTest {

    private CreateCommentService sut;

    @MockBean
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        sut = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        // given
        Comment expected = new Comment(1L, 1L, "Harry", "좋아요");
        given(commentRepository.save(expected)).willReturn(expected);

        // when
        CreateCommentResponse comment = sut.createComment(expected);

        // then
        assertThat(comment.getId()).isEqualTo(expected.getId().toString());
        assertThat(comment.getPostId()).isEqualTo(expected.getPostId().toString());
        assertThat(comment.getAuthor()).isEqualTo(expected.getAuthor());
        assertThat(comment.getContent()).isEqualTo(expected.getContent());

    }
}
