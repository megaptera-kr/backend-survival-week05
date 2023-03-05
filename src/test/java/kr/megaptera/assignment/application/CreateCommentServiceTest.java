package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
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
    @DisplayName("Create Comment")
    void creat () {
        String postId = "POST_ID_01";
        CommentCreateDto commentCreateDto = new CommentCreateDto("AUTHOR_001", "CONTENT_001 : CREATE TESTING");
        CommentDto created = createCommentService.createComment(postId, commentCreateDto);

        assertThat(created.getAuthor()).isEqualTo("AUTHOR_001");
        assertThat(created.getContent()).isEqualTo("CONTENT_001 : CREATE TESTING");
        assertThat(created.getPostId()).isEqualTo("POST_ID_01");
        assertThat(created.getId()).isNotEmpty();

    }

}
