package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CreateCommentServiceTest {
    private CreateCommentService createCommentService;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        createCommentService = new CreateCommentService(postRepository, commentRepository);
    }

    @Test
    @DisplayName("댓글 생성")
    void create() {
        // given
        given(postRepository.find(PostId.of("0001POST")))
                .willReturn(Optional.of(new Post(
                                new PostId("0001POST"),
                                "제목1",
                                "작성자1",
                                new MultilineText("내용")
                        )));



        // when
        CommentDto created = createCommentService.create("0001POST",
                                        new CommentCreateDto("작성자2", "내용2"));

        // then
        assertThat(created).isNotNull();
        assertThat(created.getAuthor()).isEqualTo("작성자2");
        assertThat(created.getContent()).isEqualTo("내용2");
    }
}
