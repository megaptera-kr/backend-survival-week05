package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCommentServiceTest {
    private DeleteCommentService deleteCommentService;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository, postRepository);
    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() {
        // given
        given(postRepository.find(PostId.of("0001POST")))
                .willReturn(Optional.of(new Post(
                        new PostId("0001POST"),
                        "제목1",
                        "작성자1",
                        new MultilineText("내용1")
                )));

        given(commentRepository.find(CommentId.of("0001COMMENT"), PostId.of("0001POST")))
                .willReturn(Optional.of(new Comment(
                        PostId.of("0001POST"),
                        "작성자2",
                        "내용2")
                ));

        // when
        CommentDto dto = deleteCommentService.delete("0001POST", "0001COMMENT");

        // then
        assertThat(dto).isNotNull();
        assertThat(dto.getAuthor()).isEqualTo("작성자2");
        assertThat(dto.getContent()).isEqualTo("내용2");
    }
}
