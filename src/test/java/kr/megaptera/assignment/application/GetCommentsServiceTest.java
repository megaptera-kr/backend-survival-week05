package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetCommentsServiceTest {
    @SpyBean
    private CommentRepository commentRepository;

    @Autowired
    private GetCommentsService getCommentsService;

    private final List<PostId> postIdList = IntStream.range(0, 2).mapToObj(i -> PostId.generate()).toList();

    private final List<CommentId> commentIdList = IntStream.range(0, 20).mapToObj(i -> CommentId.generate()).toList();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < postIdList.size(); i++) {
            PostId postId = postIdList.get(i);
            for (int j = 0; j < 10; j++) {
                int commentIdIndex = i * 10 + j;
                CommentId commentId = commentIdList.get(commentIdIndex);
                commentRepository.save(new Comment(
                        commentId, postId,
                        SingleLineText.of(String.format("Post %s, Comment %s", postId.id(), commentId.id())),
                        MultiLineText.of("this is the multilinetext")
                ));
            }
        }
    }

    @Test
    @DisplayName("댓글 로드 테스트")
    void update() {
        List<CommentDetailDto> commentList = getCommentsService.getComments(postIdList.get(0).id());

        assertThat(commentList).hasSize(10);
    }
}
