package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateCommentServiceTest {

    private CommentRepository commentRepository;
    private CreateCommentService createCommentService;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepository();
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("코멘트 생성")
    void create() {
        var postId = "001POST";
        var createDto = new CommentCreateDto("작성자", "코멘트");
        var readDto = createCommentService.execute(postId, createDto);

        assertThat(createDto.getAuthor()).isEqualTo(readDto.getAuthor());
        assertThat(createDto.getContent()).isEqualTo(readDto.getContent());
    }
}
