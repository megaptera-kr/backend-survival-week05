package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        String postId = "001";

        CommentDto commentDto = createCommentService.addCommentDto(postId,
                new CommentDto(new Comment(PostId.of(postId), "author1", "content1")));
        assertThat(commentDto).isNotNull();
    }

}
