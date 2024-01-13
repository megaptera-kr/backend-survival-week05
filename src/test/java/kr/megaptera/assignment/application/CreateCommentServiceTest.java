package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class CreateCommentServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private CommentRepository commentRepository;
    @Autowired
    private CreateCommentService createCommentService;

    @Test
    @DisplayName("댓글 작성")
    void create() {
        createCommentService.createComment(PostId.generate().id(), new CommentCreateDto("Author", "Content"));
        verify(commentRepository).save(argThat(comment -> comment.getContent().toString().equals("Content")));
    }

}
