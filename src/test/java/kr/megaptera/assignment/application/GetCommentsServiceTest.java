package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.CommentDto;
import kr.megaptera.assignment.domains.model.Comment;
import kr.megaptera.assignment.domains.model.CommentAuthor;
import kr.megaptera.assignment.domains.model.CommentId;
import kr.megaptera.assignment.domains.model.MultilineText;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {

    private CommentRepository commentRepository;

    private GetCommentsService getCommentsService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);
    }

    @Test
    @DisplayName("게시물별 댓글 목록 조회")
    void listByPost() {
        given(commentRepository.findByPost(PostId.of("1")))
                .willReturn(
                        List.of(
                                new Comment(
                                        CommentId.of("1"),
                                        PostId.of("1"),
                                        CommentAuthor.of("케이"),
                                        MultilineText.of("감사합니다!\n운영자님")
                                ),
                                new Comment(
                                        CommentId.of("2"),
                                        PostId.of("1"),
                                        CommentAuthor.of("종희"),
                                        MultilineText.of("반갑습니다!! 잘 이용할게요.")
                                )
                        )
                );

        List<CommentDto> commentDtos = getCommentsService.getComments("1");

        assertThat(commentDtos.size()).isEqualTo(2);
        assertThat(commentDtos.get(0).getAuthor()).contains("케이");
        assertThat(commentDtos.get(1).getContent()).contains("반갑습니다");
    }

}
