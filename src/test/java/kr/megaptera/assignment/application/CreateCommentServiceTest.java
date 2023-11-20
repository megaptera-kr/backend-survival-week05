package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostAuthor;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CreateCommentServiceTest {
    @SpyBean
    private CommentRepository commentRepository;
    @SpyBean
    private CreateCommentService createCommentService;
    @Autowired
    private PostRepository postRepository;

    private final String POST_ID = "postId";

    @Test
    @DisplayName("댓글 생성")
    void create() {

        postRepository.save(new Post(PostId.of(POST_ID), PostTitle.of("제목"), PostAuthor.of("글쓴이"), MultilineText.of("내용")));
        CommentDto newCommentDto = new CommentDto("commentId", "commentWriter", "commentContent");

        CommentDto createdCommentDto = createCommentService.createCommentDto(POST_ID, newCommentDto);

        Assertions.assertThat(createdCommentDto)
                .isNotNull();

        verify(commentRepository).save(any(Comment.class));
        verify(createCommentService).createCommentDto(POST_ID, newCommentDto);
    }
}
