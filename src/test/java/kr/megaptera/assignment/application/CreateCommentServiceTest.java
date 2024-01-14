package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CreateCommentServiceTest {

    private CommentRepository commentRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        //given
        Comment comment = Comment.builder()
                .id("1")
                .postId("1")
                .content("content")
                .author("author")
                .build();

        //when
        CommentDto commentDto = createCommentService.createComment(comment.getPostId(), new CommentDto(comment));

        //then
        assertThat(commentDto.getContent()).isEqualTo("content");
        assertThat(commentDto.getAuthor()).isEqualTo("author");
    }

}
