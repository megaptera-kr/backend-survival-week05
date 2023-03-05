package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DeleteCommentServiceTest {

    private CommentRepository commentRepository;

    private DeleteCommentService deleteCommentService;

    @BeforeEach
    void setUp(){
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }
    @Test
    @DisplayName("DELETE COMMENT")
    void deleteComment(){
        String id = "COMMENT_001";
        String postId = "POST_ID_001";

        Comment comment = new Comment(
                CommentId.of("COMMENT_001"),
                PostId.of("POST_ID_001"),
                "AUTHOR_001",
                "CONTENT_001");
        given(commentRepository.find(comment.id(), comment.postId())).willReturn(comment);

        CommentDto deleted = deleteCommentService.deletePost(id,postId);

        assertThat(deleted.getId()).isEqualTo(comment.id().toString());
        assertThat(deleted.getAuthor()).isEqualTo("AUTHOR_001");
        assertThat(deleted.getContent()).isEqualTo("CONTENT_001");
        assertThat(deleted.getPostId()).isEqualTo("POST_ID_001");
    }
}
