package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteCommentServiceTest {

    private PostRepository postRepository = new PostRepository();
    private CommentRepository commentRepository = new CommentRepository();

    @Test
    @DisplayName("댓글 삭제")
    void delete() {

        Post test1 = new Post(
                new PostId("01TEST"),
                "테스트제목",
                "테스트작성자",
                new MultilineText("테스트내용")
        );

        postRepository.save(test1);

        Comment testcmt1 = new Comment(
                new CommentId("001TEST"),
                new PostId("01TEST"),
                "테스트댓글작성자",
                new MultilineText("테스트댓글내용")
        );

        commentRepository.save(testcmt1);

        Comment testcmt2 = new Comment(
                new CommentId("002TEST"),
                new PostId("01TEST"),
                "테스트댓글작성자",
                new MultilineText("테스트댓글내용")
        );

        commentRepository.save(testcmt2);

        commentRepository.delete(new CommentId("002TEST"));

        List<Comment> postcmt1 = commentRepository.findAll(new PostId("01TEST"));

        assertThat(postcmt1.size()).isEqualTo(1);

    }

}
