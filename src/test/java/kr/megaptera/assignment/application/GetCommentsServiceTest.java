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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
class GetCommentsServiceTest {
    @SpyBean
    private CommentRepository commentRepository;

    @SpyBean
    private GetCommentsService getCommentsService;

    @Autowired
    private PostRepository postRepository;

    private final String POST_ID = "1";

    @Test
    @DisplayName("댓글 목록 조회")
    void delete() {
        Post post = new Post(PostId.of(POST_ID), PostTitle.of("제목"), PostAuthor.of("작성자"), MultilineText.of("내용"));
        postRepository.save(post);

        Comment comment1 = new Comment(PostId.of(POST_ID), CommentAuthor.of("commentAuthor1"), MultilineText.of("commentContent1"));
        commentRepository.save(comment1);
        Comment comment2 = new Comment(PostId.of(POST_ID), CommentAuthor.of("commentAuthor2"), MultilineText.of("commentContent2"));
        commentRepository.save(comment2);

        List<CommentDto> comments = getCommentsService.getCommentDtos(POST_ID);

        assertThat(comments.size()).isEqualTo(2);

        verify(getCommentsService).getCommentDtos(POST_ID);
        verify(commentRepository).findAll();

    }


}
