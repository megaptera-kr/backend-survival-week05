package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.megaptera.assignment.AssignmentApplication;
import kr.megaptera.assignment.dtos.comments.CommentCreateDto;
import kr.megaptera.assignment.dtos.comments.CommentReadDto;
import kr.megaptera.assignment.dtos.comments.CommentUpdateDto;
import kr.megaptera.assignment.entities.CommentEntity;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = AssignmentApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {
    @Value("${local.server.port}")
    private int port;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("GET /comments")
    void list() throws JsonProcessingException {
        var commentEntity = new CommentEntity("001COMMENT", "001POST", "Author","Content");
        commentRepository.add(commentEntity);

        var url = "http://localhost:" + port + "/comments?postId=001POST";
        var dtos = restTemplate.getForObject(url, CommentReadDto[].class);

        assertThat(dtos.length).isEqualTo(1);
    }

    @Test
    @DisplayName("POST /comments")
    void create() {
        var url = "http://localhost:" + port + "/comments?postId=002POST";

        var commentCreateDto = new CommentCreateDto("author", "content");
        var commentReadDto = restTemplate.postForObject(url, commentCreateDto, CommentReadDto.class);

        assertThat(commentReadDto.getAuthor()).isEqualTo(commentCreateDto.getAuthor());
        assertThat(commentReadDto.getContent()).isEqualTo(commentCreateDto.getContent());
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() {
        var commentEntity = new CommentEntity("003COMMENT", "003POST", "Author","Content");
        commentRepository.add(commentEntity);

        var url = "http://localhost:" + port + "/comments/003COMMENT";
        var commentUpdateDto = new CommentUpdateDto("Updated Content");
        // Patch == Put
        restTemplate.put(url, commentUpdateDto, CommentReadDto.class);
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() {
        var commentEntity = new CommentEntity("003COMMENT", "003POST", "Author","Content");
        commentRepository.add(commentEntity);

        var url = "http://localhost:" + port + "/comments/003COMMENT";
        restTemplate.delete(url);
    }
}
