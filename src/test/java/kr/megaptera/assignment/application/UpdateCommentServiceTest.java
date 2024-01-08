package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentAuthor;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostAuthor;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UpdateCommentServiceTest {
    @SpyBean
    private CommentRepository commentRepository;

    @SpyBean
    private UpdateCommentService updateCommentService;

    @Autowired
    private PostRepository postRepository;

    private final String POST_ID = "1";
    private final String UPDATED_COMMENT_CONTENT = "updatedContent";

    @Test
    @DisplayName("댓글 수정")
    void update() {
        Post post = new Post(PostId.of(POST_ID), PostTitle.of("제목"), PostAuthor.of("작성자"), MultilineText.of("내용"));
        postRepository.save(post);

        Comment comment = new Comment(PostId.of(POST_ID), CommentAuthor.of("commentAuthor"), MultilineText.of("commentContent"));
        commentRepository.save(comment);

        CommentDto updatedCommentDto = updateCommentService.updateCommentDto(comment.id().toString(), new CommentDto(UPDATED_COMMENT_CONTENT));

        assertThat(updatedCommentDto.getContent()).isEqualTo(UPDATED_COMMENT_CONTENT);

        verify(updateCommentService).updateCommentDto(any(String.class), any(CommentDto.class));
    }
}
