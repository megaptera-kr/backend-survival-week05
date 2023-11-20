package kr.megaptera.assignment.controllers;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {
    // TODO: PostControllerTest 를 참고해서 테스트를 작성해 주세요.
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    private final String COMMENTS_CONTROLLER_URL;

    public CommentControllerTest(@Value("${local.server.port}") int port) {
        COMMENTS_CONTROLLER_URL = "http://localhost:" + port + "/comments";
    }

    private final Post SAMPLE_POST = new Post(
            PostId.of("0001POST"),
            PostTitle.of("제목"),
            PostAuthor.of("작성자"),
            MultilineText.of("내용")
    );
    private final Comment SAMPLE_COMMENT = new Comment(
            SAMPLE_POST.id(),
            CommentAuthor.of("commentAuthor"),
            MultilineText.of("commentContent")
    );


    @BeforeEach
    void setup() {
        postRepository.clear();
        postRepository.save(SAMPLE_POST);
        commentRepository.clear();
        commentRepository.save(SAMPLE_COMMENT);
    }

    @Test
    @DisplayName("GET /comments?postId={postId}")
    void list() {
        List<CommentDto> result = restTemplate.getForObject(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST.id(), List.class);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("POST /comments?postId={postId}")
    void create() {
        CommentDto commentDto = new CommentDto("createdAuthor", "createdContent");

        restTemplate.postForLocation(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST.id(), commentDto);

        String body = restTemplate.getForObject(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST.id(), String.class);
        assertThat(body).contains("createdAuthor");
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void update() {
        CommentDto commentDto = new CommentDto("updatedContent");
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        CommentDto updatedComment = restTemplate.patchForObject(COMMENTS_CONTROLLER_URL + "/" + SAMPLE_COMMENT.id() + "?postId=" + SAMPLE_POST.id()
                , commentDto, CommentDto.class);

        assertThat(updatedComment.getId()).isEqualTo(SAMPLE_COMMENT.id().toString());
        assertThat(updatedComment.getContent()).isEqualTo(commentDto.getContent());
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deletePost() {
        restTemplate.delete(COMMENTS_CONTROLLER_URL + "/" + SAMPLE_COMMENT.id() + "?postId=" + SAMPLE_POST.id());

        List<CommentDto> result = restTemplate.getForObject(COMMENTS_CONTROLLER_URL + "?postId=" + SAMPLE_POST.id(), List.class);

        assertThat(result.size()).isEqualTo(0);
    }
}
