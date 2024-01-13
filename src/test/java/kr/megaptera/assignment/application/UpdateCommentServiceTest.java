package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UpdateCommentServiceTest {
    @SpyBean
    private CommentRepository commentRepository;

    @Autowired
    private UpdateCommentService updateCommentService;

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
    void update() {
        updateCommentService.updateComment(
                commentIdList.get(0).id(),
                postIdList.get(0).id(),
                new CommentUpdateDto("Content?"));

        assertThat(commentRepository.find(commentIdList.get(0),
                postIdList.get(0))).satisfies(comment -> {
            assertThat(comment.getContent().text()).isEqualTo("Content?");
        });
    }
}
