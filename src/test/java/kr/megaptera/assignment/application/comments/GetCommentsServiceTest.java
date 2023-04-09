package kr.megaptera.assignment.application.comments;

import kr.megaptera.assignment.entities.CommentEntity;
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
    @DisplayName("코멘트 조회")
    void list() {
        var commentEntity = new CommentEntity(
                "0001Comment",
                "0001POST",
                "작성자",
                "내용");
        var commentEntities = List.of(commentEntity);

        given(commentRepository.findByPostId(commentEntity.getPostId())).willReturn(commentEntities);

        var commentReadDtos = getCommentsService.execute(commentEntity.getPostId());
        assertThat(commentReadDtos.size()).isEqualTo(1);

        var commentReadDto = commentReadDtos.get(0);
        assertThat(commentReadDto.getId()).isEqualTo(commentEntity.getId());
        assertThat(commentReadDto.getAuthor()).isEqualTo(commentEntity.getAuthor());
        assertThat(commentReadDto.getContent()).isEqualTo(commentEntity.getContent());
    }
}
